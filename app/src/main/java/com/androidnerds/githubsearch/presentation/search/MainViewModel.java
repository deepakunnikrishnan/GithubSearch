package com.androidnerds.githubsearch.presentation.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnerds.githubsearch.core.Result;
import com.androidnerds.githubsearch.domain.model.AppError;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.usecase.SearchOrganizationRepositoriesUseCase;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final SearchOrganizationRepositoriesUseCase useCase;
    private final MediatorLiveData<Result<List<Repo>, AppError>> resultMediatorLiveData = new MediatorLiveData<>();

    public MainViewModel(SearchOrganizationRepositoriesUseCase useCase) {
        this.useCase = useCase;
        resultMediatorLiveData.addSource(useCase.getResultLiveData(), result -> {
            if (result.getData() != null) {
                Result<List<Repo>, AppError> viewResult = new Result<>(result.getData().getRepoList(), null);
                resultMediatorLiveData.postValue(viewResult);
            } else {
                Result<List<Repo>, AppError> viewResult = new Result<>(new ArrayList<>(), result.getError());
                resultMediatorLiveData.postValue(viewResult);
            }
        });

    }

    public LiveData<Result<List<Repo>, AppError>> getSearchResult() {
        return resultMediatorLiveData;
    }

    public void searchRepositoriesForOrganization(String organization) {
        this.useCase.execute(organization, 3);
    }


}
