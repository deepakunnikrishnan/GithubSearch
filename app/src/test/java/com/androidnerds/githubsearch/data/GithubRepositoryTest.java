package com.androidnerds.githubsearch.data;

import com.androidnerds.githubsearch.core.mapper.ListMapperImpl;
import com.androidnerds.githubsearch.core.mapper.Mapper;
import com.androidnerds.githubsearch.data.remote.ApiConstants;
import com.androidnerds.githubsearch.data.remote.GithubRemoteDataSource;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.domain.mapper.RepoDTOtoDomainMapper;
import com.androidnerds.githubsearch.domain.mapper.SearchResponseToDomainMapper;
import com.androidnerds.githubsearch.domain.model.SearchResult;
import com.androidnerds.githubsearch.testutils.MockResponseFileReader;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class GithubRepositoryTest {

    GithubRepository githubRepository;
    @Mock
    GithubRemoteDataSource remoteDataSource;
    Mapper<GithubSearchResponse, SearchResult> dtoToDomainMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dtoToDomainMapper = new SearchResponseToDomainMapper(new ListMapperImpl<>(new RepoDTOtoDomainMapper()));
        githubRepository = new GithubRepository(remoteDataSource, dtoToDomainMapper);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTopRepositories() {
        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.TOP_REPOSITORIES);
        GithubSearchResponse expected = new Gson().fromJson( fileReader.getContent(), GithubSearchResponse.class);
        Mockito.when(remoteDataSource.getTopRepositories(anyString(), anyInt()))
                .thenReturn(Single.just(expected));
        TestObserver<SearchResult> testObserver = githubRepository.getTopRepositories("google", 3).test();
        testObserver.assertNoErrors();
        testObserver.assertValue(searchResult -> expected.getItems().size() == searchResult.getRepoList().size()
                && expected.getTotalCount() == searchResult.getTotalCount());
        testObserver.assertComplete();
    }
}