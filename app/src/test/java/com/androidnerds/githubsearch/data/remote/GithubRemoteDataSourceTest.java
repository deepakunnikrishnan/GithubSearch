package com.androidnerds.githubsearch.data.remote;

import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.core.rx.TestScheduler;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.testutils.MockResponseFileReader;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class GithubRemoteDataSourceTest {

    @Mock
    private GithubApiService githubApiService;
    private SchedulerProvider schedulerProvider;

    private GithubRemoteDataSource remoteDataSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new TestScheduler();
        remoteDataSource = new GithubRemoteDataSource(githubApiService, schedulerProvider);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTopRepositories() {
        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.TOP_REPOSITORIES);
        GithubSearchResponse expected = new Gson().fromJson(fileReader.getContent(), GithubSearchResponse.class);
        Mockito.when(githubApiService.getTopRepositories(anyString(),
                anyString(), anyString(), anyInt()))
                .thenReturn(Single.just(expected));
        TestObserver<GithubSearchResponse> responseTestObserver = remoteDataSource.getTopRepositories("google", 3)
                .test();
        responseTestObserver.assertNoErrors();
        responseTestObserver.assertValue(githubSearchResponse -> githubSearchResponse.getItems().size() == 3);
        responseTestObserver.assertComplete();
    }
}