package com.androidnerds.githubsearch.domain;

import com.androidnerds.githubsearch.domain.model.SearchResult;

import io.reactivex.rxjava3.core.Single;

public interface IGithubRepository {

    Single<SearchResult> getTopRepositories(String organizationName, int limit);
}
