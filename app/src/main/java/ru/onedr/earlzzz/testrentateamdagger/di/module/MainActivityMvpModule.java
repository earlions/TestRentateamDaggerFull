package ru.onedr.earlzzz.testrentateamdagger.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.onedr.earlzzz.testrentateamdagger.di.scopes.ActivityScope;
import ru.onedr.earlzzz.testrentateamdagger.mvp.MainActivityContract;

@Module
public class MainActivityMvpModule {
    private  MainActivityContract.View mView;
    private Context context;


    public MainActivityMvpModule(MainActivityContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Provides
    @ActivityScope
    Context context() {
        return context;
    }
    @Provides
    @ActivityScope
    MainActivityContract.View provideView() {
        return mView;
    }


}
