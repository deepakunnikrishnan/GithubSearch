package com.androidnerds.githubsearch.presentation.search

import com.androidnerds.githubsearch.domain.model.Repo

sealed class SearchViewState {

    object Loading: SearchViewState()
    object Error: SearchViewState()

    data class SearchResultFetched(val repoList: List<Repo>): SearchViewState()

}