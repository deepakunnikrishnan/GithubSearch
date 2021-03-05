package com.androidnerds.githubsearch.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidnerds.githubsearch.core.Result;
import com.androidnerds.githubsearch.core.annotation.ErrorType;
import com.androidnerds.githubsearch.core.remote.NetworkException;
import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.data.remote.dto.ApiError;
import com.androidnerds.githubsearch.data.remote.dto.ApiErrorResponse;
import com.androidnerds.githubsearch.domain.IGithubRepository;
import com.androidnerds.githubsearch.domain.model.AppError;
import com.androidnerds.githubsearch.domain.model.SearchResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class SearchOrganizationRepositoriesUseCase {

    private final IGithubRepository githubRepository;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Result<SearchResult, AppError>> resultLiveData = new MutableLiveData<>();

    @Inject
    public SearchOrganizationRepositoriesUseCase(IGithubRepository githubRepository,
                                                 SchedulerProvider schedulerProvider) {
        this.githubRepository = githubRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void execute(String organizationName, int limit) {
        Disposable disposable = githubRepository.getTopRepositories(organizationName, limit)
                .doOnError(throwable -> {
                    if(throwable instanceof NetworkException) {
                        NetworkException exception = (NetworkException) throwable;
                        if(exception.getError() instanceof ApiErrorResponse) {
                            ApiErrorResponse apiErrorResponse = (ApiErrorResponse) exception.getError();
                            Result<SearchResult, AppError> result = new Result<>(null, new AppError(AppError.ErrorType.API_ERROR, apiErrorResponse.getErrors().get(0).getMessage()));
                            resultLiveData.postValue(result);
                        } else {
                            Result<SearchResult, AppError> result = new Result<>(null, new AppError(AppError.ErrorType.NETWORK_ERROR, exception.getMessage()));
                            resultLiveData.postValue(result);
                        }
                    } else {
                        Result<SearchResult, AppError> result = new Result<>(null, new AppError(AppError.ErrorType.NETWORK_ERROR, throwable.getMessage()));
                        resultLiveData.postValue(result);
                    }
                })
                .subscribeOn(schedulerProvider.io())
                .subscribe(searchResult -> {
                    Result<SearchResult, AppError> result = new Result<>(searchResult, null);
                    resultLiveData.postValue(result);
                }, throwable -> {});
        compositeDisposable.add(disposable);
    }

    public LiveData<Result<SearchResult, AppError>> getResultLiveData() {
        return resultLiveData;
    }

    public void clear() {
        compositeDisposable.clear();
    }
}
