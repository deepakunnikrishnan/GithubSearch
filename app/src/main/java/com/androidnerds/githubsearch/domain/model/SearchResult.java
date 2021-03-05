package com.androidnerds.githubsearch.domain.model;

import java.util.List;

public class SearchResult {

    private int totalCount;
    private List<Repo> repoList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
    }
}
