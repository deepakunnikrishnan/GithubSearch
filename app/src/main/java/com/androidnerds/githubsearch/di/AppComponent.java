package com.androidnerds.githubsearch.di;

import android.content.Context;

import com.androidnerds.githubsearch.presentation.search.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    Context context();
    void inject(MainActivity mainActivity);
}
