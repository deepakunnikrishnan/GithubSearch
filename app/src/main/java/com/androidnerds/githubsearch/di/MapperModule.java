package com.androidnerds.githubsearch.di;

import com.androidnerds.githubsearch.core.mapper.ListMapper;
import com.androidnerds.githubsearch.core.mapper.ListMapperImpl;
import com.androidnerds.githubsearch.core.mapper.Mapper;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.data.remote.dto.RepoDTO;
import com.androidnerds.githubsearch.domain.mapper.RepoDTOtoDomainMapper;
import com.androidnerds.githubsearch.domain.mapper.SearchResponseToDomainMapper;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.model.SearchResult;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MapperModule {

    @Binds
    public abstract Mapper<GithubSearchResponse, SearchResult> bindGithubSearchResponseToDomainMapper(SearchResponseToDomainMapper mapper);

    @Binds
    public abstract Mapper<RepoDTO, Repo> bindRepoDTOtoDomainMapper(RepoDTOtoDomainMapper mapper);

    @Provides
    public static ListMapper<RepoDTO, Repo> provideDayForecastDtoListToDomainMapper(Mapper<RepoDTO, Repo> mapper) {
        return new ListMapperImpl<>(mapper);
    }


}
