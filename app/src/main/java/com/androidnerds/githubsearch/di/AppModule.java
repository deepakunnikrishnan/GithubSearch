package com.androidnerds.githubsearch.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        DataModule.class,
        MapperModule.class,
        DomainModule.class,
        SchedulerProviderModule.class
})
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

}


