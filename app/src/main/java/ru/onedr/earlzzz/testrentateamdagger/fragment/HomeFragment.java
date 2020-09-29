package ru.onedr.earlzzz.testrentateamdagger.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import javax.inject.Inject;

import ru.onedr.earlzzz.testrentateamdagger.MyApplication;
import ru.onedr.earlzzz.testrentateamdagger.R;
import ru.onedr.earlzzz.testrentateamdagger.RecyclerViewAdapter;
import ru.onedr.earlzzz.testrentateamdagger.di.component.ApplicationComponent;
import ru.onedr.earlzzz.testrentateamdagger.di.component.DaggerMainActivityComponent;
import ru.onedr.earlzzz.testrentateamdagger.di.component.MainActivityComponent;
import ru.onedr.earlzzz.testrentateamdagger.di.module.MainActivityContextModule;
import ru.onedr.earlzzz.testrentateamdagger.di.module.MainActivityMvpModule;
import ru.onedr.earlzzz.testrentateamdagger.di.qualifier.ActivityContext;
import ru.onedr.earlzzz.testrentateamdagger.di.qualifier.ApplicationContext;
import ru.onedr.earlzzz.testrentateamdagger.mvp.MainActivityContract;
import ru.onedr.earlzzz.testrentateamdagger.mvp.PresenterImpl;
import ru.onedr.earlzzz.testrentateamdagger.pojo.Post;


public class HomeFragment extends Fragment implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;


    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container,
                false);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this,getContext()))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        progressBar=view.findViewById(R.id.progressBar);
        mRecyclerView = view.findViewById(R.id.settings_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        presenter.loadData();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }




    @Override
    public void showData(List<Post> mPost) {
        recyclerViewAdapter.setData(mPost);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void launchIntent(String name) {

    }
}
