package com.androidnerds.githubsearch.data;

import com.androidnerds.githubsearch.core.mapper.Mapper;
import com.androidnerds.githubsearch.data.remote.GithubRemoteDataSource;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.domain.IGithubRepository;
import com.androidnerds.githubsearch.domain.model.SearchResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GithubRepository implements IGithubRepository {

    private final GithubRemoteDataSource githubRemoteDataSource;
    private final Mapper<GithubSearchResponse, SearchResult> dtoToDomainMapper;

    @Inject
    public GithubRepository(GithubRemoteDataSource githubRemoteDataSource,
                            Mapper<GithubSearchResponse, SearchResult> dtoToDomainMapper) {
        this.githubRemoteDataSource = githubRemoteDataSource;
        this.dtoToDomainMapper = dtoToDomainMapper;
    }

    @Override
    public Single<SearchResult> getTopRepositories(String organizationName, int limit) {
        return githubRemoteDataSource
                .getTopRepositories(organizationName, limit)
                .map(dtoToDomainMapper::map);
    }
}
