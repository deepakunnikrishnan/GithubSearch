package com.androidnerds.githubsearch.di;

import com.androidnerds.githubsearch.data.GithubRepository;
import com.androidnerds.githubsearch.domain.IGithubRepository;

import dagger.Binds;
import dagger.Module;

@Module(includes = {RemoteModule.class})
public abstract class DataModule {

    @Binds
    public abstract IGithubRepository provideGithubRepository(GithubRepository githubRepository);

}
