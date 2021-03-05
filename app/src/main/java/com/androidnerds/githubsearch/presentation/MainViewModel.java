package com.androidnerds.githubsearch.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.androidnerds.githubsearch.core.Result;
import com.androidnerds.githubsearch.data.remote.dto.ApiError;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.domain.model.SearchResult;
import com.androidnerds.githubsearch.domain.usecase.SearchOrganizationRepositoriesUseCase;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final SearchOrganizationRepositoriesUseCase useCase;
    private MediatorLiveData<List<Repo>> resultMediatorLiveData = new MediatorLiveData<>();

    public MainViewModel(SearchOrganizationRepositoriesUseCase useCase) {
        this.useCase = useCase;
        resultMediatorLiveData.addSource(useCase.getResultLiveData(), new Observer<Result<SearchResult, ApiError>>() {
            @Override
            public void onChanged(Result<SearchResult, ApiError> result) {
                if(result.getData() != null) {
                    resultMediatorLiveData.postValue(result.getData().getRepoList());
                }
            }
        });

    }

    public LiveData<List<Repo>> getRepoList() {
        return resultMediatorLiveData;
    }

    public void searchRepositoriesForOrganization(String organization) {
        this.useCase.execute(organization, 3);
    }


}
