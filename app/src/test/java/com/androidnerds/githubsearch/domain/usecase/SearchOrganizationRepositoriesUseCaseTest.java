package com.androidnerds.githubsearch.domain.usecase;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.androidnerds.githubsearch.core.Result;
import com.androidnerds.githubsearch.core.remote.NetworkException;
import com.androidnerds.githubsearch.core.rx.DefaultScheduler;
import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.data.remote.ApiConstants;
import com.androidnerds.githubsearch.data.remote.dto.ApiError;
import com.androidnerds.githubsearch.data.remote.dto.ApiErrorResponse;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;
import com.androidnerds.githubsearch.domain.IGithubRepository;
import com.androidnerds.githubsearch.domain.model.AppError;
import com.androidnerds.githubsearch.domain.model.SearchResult;
import com.androidnerds.githubsearch.testutils.MockResponseFileReader;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.HttpException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class SearchOrganizationRepositoriesUseCaseTest {

    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private IGithubRepository githubRepository;

    @Mock
    private Observer<Result<SearchResult, AppError>> observer;
    @Captor
    private ArgumentCaptor<Result<SearchResult, AppError>> argumentCaptor;

    private SchedulerProvider schedulerProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new DefaultScheduler();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecuteSuccess() {
        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.SEARCH_RESULTS);
        SearchResult expected = new Gson().fromJson( fileReader.getContent(), SearchResult.class);
        Mockito.when(githubRepository.getTopRepositories(anyString(), anyInt()))
                .thenReturn(Single.just(expected));

        SearchOrganizationRepositoriesUseCase useCase = new SearchOrganizationRepositoriesUseCase(githubRepository,
                schedulerProvider);
        useCase.getResultLiveData().observeForever(observer);
        useCase.execute("testname",3);

        //verify
        Mockito.verify(observer).onChanged(argumentCaptor.capture());
        Assert.assertNotNull(argumentCaptor.getValue());
        Assert.assertNotNull(argumentCaptor.getValue().getData());
        Assert.assertEquals(3, argumentCaptor.getValue().getData().getRepoList().size());
        Assert.assertEquals(76, argumentCaptor.getValue().getData().getTotalCount());
    }

    /*@Test
    public void testExecuteFailure() {
        String message = "The listed users and repositories cannot be searched either because the resources do not exist or you do not have permission to view them.";
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage("Validation Failed");
        List<ApiError> apiErrorList = new ArrayList<>();
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setCode("invalid");
        apiError.setField("q");
        apiError.setResource("Search");
        apiErrorList.add(apiError);
        apiErrorResponse.setErrors(apiErrorList);

        Mockito.when(githubRepository.getTopRepositories(anyString(), anyInt()))
                .thenReturn(Single.error(new NetworkException(new Throwable(), apiErrorResponse)));

        SearchOrganizationRepositoriesUseCase useCase = new SearchOrganizationRepositoriesUseCase(githubRepository,
                schedulerProvider);
        useCase.getResultLiveData().observeForever(observer);
        useCase.execute("testname",3);

        //verify
        Mockito.verify(observer).onChanged(argumentCaptor.capture());
        Assert.assertNotNull(argumentCaptor.getValue());
        Assert.assertNotNull(argumentCaptor.getValue().getError());
        AppError appError = argumentCaptor.getValue().getError();
        Assert.assertEquals(AppError.ErrorType.API_ERROR, appError.getErrorType());
        Assert.assertEquals(message, appError.getMessage());
    }*/
}