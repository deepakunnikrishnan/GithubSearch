package com.androidnerds.githubsearch.presentation.search;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.androidnerds.githubsearch.core.Result;
import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.core.rx.TestScheduler;
import com.androidnerds.githubsearch.data.remote.ApiConstants;
import com.androidnerds.githubsearch.domain.IGithubRepository;
import com.androidnerds.githubsearch.domain.model.AppError;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.model.SearchResult;
import com.androidnerds.githubsearch.domain.usecase.SearchOrganizationRepositoriesUseCase;
import com.androidnerds.githubsearch.testutils.MockResponseFileReader;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(JUnit4.class)
public class MainViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    private MainViewModel mainViewModel;
    @Mock
    private IGithubRepository githubRepository;
    private SchedulerProvider schedulerProvider;
    private SearchOrganizationRepositoriesUseCase useCase;
    @Mock
    private Observer<Result<List<Repo>, AppError>> observer;
    @Captor
    private ArgumentCaptor<Result<List<Repo>, AppError>> argumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new TestScheduler();
        useCase = new SearchOrganizationRepositoriesUseCase(githubRepository, schedulerProvider);
        mainViewModel = new MainViewModel(useCase);
    }

    @Test
    public void getSearchResultSuccess() {
        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.SEARCH_RESULTS);
        SearchResult expected = new Gson().fromJson( fileReader.getContent(), SearchResult.class);
        Mockito.when(githubRepository.getTopRepositories(anyString(),anyInt()))
                .thenReturn(Single.just(expected));
        mainViewModel.getSearchResult()
                .observeForever(observer);
        mainViewModel.searchRepositoriesForOrganization("google");
        Mockito.verify(observer).onChanged(argumentCaptor.capture());
        Assert.assertNotNull(argumentCaptor.getValue());
        Result<List<Repo>, AppError> result = argumentCaptor.getValue();
        assertNotNull(result.getData());
        assertEquals(3, result.getData().size());
    }
}