package ru.onedr.earlzzz.testrentateamdagger.di.module;


import dagger.Module;
import dagger.Provides;
import ru.onedr.earlzzz.testrentateamdagger.RecyclerViewAdapter;
import ru.onedr.earlzzz.testrentateamdagger.di.scopes.ActivityScope;
import ru.onedr.earlzzz.testrentateamdagger.fragment.HomeFragment;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(HomeFragment mainActivity) {
        return mainActivity;
    }
}
