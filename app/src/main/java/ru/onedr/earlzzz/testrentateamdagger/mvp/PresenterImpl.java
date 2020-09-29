package ru.onedr.earlzzz.testrentateamdagger.mvp;

import android.content.Context;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.onedr.earlzzz.testrentateamdagger.di.DBHelper.DBHelper;
import ru.onedr.earlzzz.testrentateamdagger.pojo.Post;
import ru.onedr.earlzzz.testrentateamdagger.retrofit.APIInterface;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;
    private DBHelper dbHelper;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView, Context context) {
        this.apiInterface = apiInterface;
        this.mView = mView;
        this.dbHelper= new DBHelper(context);
    }

    @Override
    public void loadData() {
        final List<Post> mPost = new ArrayList<>();
        final List<Post> mPostDB = new ArrayList<>();
        mView.showProgress();

        apiInterface.getAllPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {


                    @Override
                    public void onError(Throwable e) {
                        mPostDB.addAll(dbHelper.getData());
                        mView.showComplete();
                        mView.showData(mPostDB);
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject js) {
                        JsonObject jsonObject;
                        int size=  js.getAsJsonArray("data").size();
                        for (int n=0;n<size;n++){
                            jsonObject=js.getAsJsonArray("data").get(n).getAsJsonObject();
                            long id =jsonObject.get("id").getAsLong();
                            String firsName=jsonObject.get("first_name").getAsString();
                            String lastName=jsonObject.get("last_name").getAsString();
                            String email=jsonObject.get("email").getAsString();
                            String avatarSrc=jsonObject.get("avatar").getAsString();
                            Post post= new Post(id,firsName,lastName,email,avatarSrc);
                            mPost.add(post);}
                        if (dbHelper.getcount()==0)
                            dbHelper.insertContact(mPost);


                        mView.showData(mPost);
                    }
                });
    }
}
