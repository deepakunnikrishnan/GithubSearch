package com.androidnerds.githubsearch.di;

import android.content.Context;

import com.androidnerds.githubsearch.presentation.MainActivity;

import dagger.Component;

@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    Context context();
    void inject(MainActivity mainActivity);
}
