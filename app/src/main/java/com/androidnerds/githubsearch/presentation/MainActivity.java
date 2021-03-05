package com.androidnerds.githubsearch.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.androidnerds.githubsearch.R;
import com.androidnerds.githubsearch.di.AppModule;
import com.androidnerds.githubsearch.di.DaggerAppComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainViewModelFactory mainViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerAppComponent
                .builder().
                appModule(new AppModule(this))
                .build()
                .inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this, mainViewModelFactory).get(MainViewModel.class);
    }
}