package ru.onedr.earlzzz.testrentateamdagger.di.module;

import android.content.Context;


import dagger.Module;
import dagger.Provides;
import ru.onedr.earlzzz.testrentateamdagger.MainActivity;
import ru.onedr.earlzzz.testrentateamdagger.di.qualifier.ActivityContext;
import ru.onedr.earlzzz.testrentateamdagger.di.scopes.ActivityScope;
import ru.onedr.earlzzz.testrentateamdagger.fragment.HomeFragment;

@Module
public class MainActivityContextModule {
    private HomeFragment mainActivity;

    public Context context;

    public MainActivityContextModule(HomeFragment mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity.getContext();
    }

    @Provides
    @ActivityScope
    public HomeFragment providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
