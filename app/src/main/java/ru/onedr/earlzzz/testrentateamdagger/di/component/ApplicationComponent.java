package ru.onedr.earlzzz.testrentateamdagger.di.component;

import android.content.Context;


import dagger.Component;
import ru.onedr.earlzzz.testrentateamdagger.MyApplication;
import ru.onedr.earlzzz.testrentateamdagger.di.module.ContextModule;
import ru.onedr.earlzzz.testrentateamdagger.di.module.RetrofitModule;
import ru.onedr.earlzzz.testrentateamdagger.di.qualifier.ApplicationContext;
import ru.onedr.earlzzz.testrentateamdagger.di.scopes.ApplicationScope;
import ru.onedr.earlzzz.testrentateamdagger.retrofit.APIInterface;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
