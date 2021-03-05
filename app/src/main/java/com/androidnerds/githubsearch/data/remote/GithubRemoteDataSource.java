package com.androidnerds.githubsearch.data.remote;

import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GithubRemoteDataSource {

    private final GithubApiService githubApiService;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public GithubRemoteDataSource(GithubApiService githubApiService, SchedulerProvider schedulerProvider) {
        this.githubApiService = githubApiService;
        this.schedulerProvider = schedulerProvider;
    }

    public Single<GithubSearchResponse> getTopRepositories(String organizationName, int limit) {
        return githubApiService.getTopRepositories("org:"+organizationName, "stars", "desc", limit)
                .subscribeOn(schedulerProvider.io());
    }
}
