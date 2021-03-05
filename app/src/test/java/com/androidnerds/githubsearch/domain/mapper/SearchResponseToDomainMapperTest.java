package com.androidnerds.githubsearch.domain.mapper;

import com.androidnerds.githubsearch.core.mapper.ListMapper;
import com.androidnerds.githubsearch.core.mapper.ListMapperImpl;
import com.androidnerds.githubsearch.data.remote.ApiConstants;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.data.remote.dto.RepoDTO;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.model.SearchResult;
import com.androidnerds.githubsearch.testutils.MockResponseFileReader;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchResponseToDomainMapperTest {

    @Test
    public void map() {
        ListMapper<RepoDTO, Repo> listMapper = new ListMapperImpl<>(new RepoDTOtoDomainMapper());
        SearchResponseToDomainMapper mapper = new SearchResponseToDomainMapper(listMapper);

        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.TOP_REPOSITORIES);
        GithubSearchResponse expected = new Gson().fromJson( fileReader.getContent(), GithubSearchResponse.class);
        SearchResult searchResult = mapper.map(expected);
        assertNotNull(searchResult);
        assertEquals(3, searchResult.getRepoList().size());
        assertEquals(76, searchResult.getTotalCount());
        String result = new Gson().toJson(searchResult);
    }
}