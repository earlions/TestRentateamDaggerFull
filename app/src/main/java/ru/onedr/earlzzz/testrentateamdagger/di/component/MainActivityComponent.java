package ru.onedr.earlzzz.testrentateamdagger.di.component;

import android.content.Context;


import dagger.Component;
import ru.onedr.earlzzz.testrentateamdagger.di.module.AdapterModule;
import ru.onedr.earlzzz.testrentateamdagger.di.module.MainActivityMvpModule;
import ru.onedr.earlzzz.testrentateamdagger.di.qualifier.ActivityContext;
import ru.onedr.earlzzz.testrentateamdagger.di.scopes.ActivityScope;
import ru.onedr.earlzzz.testrentateamdagger.fragment.HomeFragment;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(HomeFragment mainActivity);
}
