package com.androidnerds.githubsearch.presentation;

import androidx.lifecycle.ViewModel;

import com.androidnerds.githubsearch.domain.usecase.SearchOrganizationRepositoriesUseCase;

public class MainViewModel extends ViewModel {

    private final SearchOrganizationRepositoriesUseCase useCase;

    public MainViewModel(SearchOrganizationRepositoriesUseCase useCase) {
        this.useCase = useCase;
        searchRepositoriesForOrganization("deepak-unnikrishnan");
    }

    private void searchRepositoriesForOrganization(String organization) {
        this.useCase.execute(organization, 3);
    }


}
