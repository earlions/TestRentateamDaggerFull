package ru.onedr.earlzzz.testrentateamdagger;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import ru.onedr.earlzzz.testrentateamdagger.di.component.ApplicationComponent;
import ru.onedr.earlzzz.testrentateamdagger.di.component.DaggerApplicationComponent;
import ru.onedr.earlzzz.testrentateamdagger.di.module.ContextModule;
import ru.onedr.earlzzz.testrentateamdagger.fragment.HomeFragment;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Fragment activity){
        return (MyApplication) activity.getActivity().getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

