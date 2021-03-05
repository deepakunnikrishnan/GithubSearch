package com.androidnerds.githubsearch.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.androidnerds.githubsearch.domain.usecase.SearchOrganizationRepositoriesUseCase;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final SearchOrganizationRepositoriesUseCase useCase;

    @Inject
    public MainViewModelFactory(SearchOrganizationRepositoriesUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(useCase);
        }
        return null;
    }
}
