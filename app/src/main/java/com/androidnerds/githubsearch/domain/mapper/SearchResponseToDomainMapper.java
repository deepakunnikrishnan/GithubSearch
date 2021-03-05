package com.androidnerds.githubsearch.domain.mapper;

import com.androidnerds.githubsearch.core.mapper.ListMapper;
import com.androidnerds.githubsearch.core.mapper.Mapper;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.data.remote.dto.RepoDTO;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.model.SearchResult;

import javax.inject.Inject;

public class SearchResponseToDomainMapper implements Mapper<GithubSearchResponse, SearchResult> {

    private final ListMapper<RepoDTO, Repo> repoDTOtoListMapper;

    @Inject
    public SearchResponseToDomainMapper(ListMapper<RepoDTO, Repo> repoDTOtoListMapper) {
        this.repoDTOtoListMapper = repoDTOtoListMapper;
    }

    @Override
    public SearchResult map(GithubSearchResponse input) {
        SearchResult searchResult = new SearchResult();
        searchResult.setTotalCount(input.getTotalCount());
        searchResult.setRepoList(repoDTOtoListMapper.map(input.getItems()));
        return searchResult;
    }
}
