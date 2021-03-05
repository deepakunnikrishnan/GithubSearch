package com.androidnerds.githubsearch.di;

import com.androidnerds.githubsearch.core.rx.DefaultScheduler;
import com.androidnerds.githubsearch.core.rx.SchedulerProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SchedulerProviderModule {

    @Binds
    public abstract SchedulerProvider bindSchedulerProvider(DefaultScheduler defaultScheduler);
}
