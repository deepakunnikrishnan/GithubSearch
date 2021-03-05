package com.androidnerds.githubsearch.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubSearchResponse {

    @SerializedName("total_count")
    private int totalCount;

    private List<RepoDTO> items;

    private String message;
    @SerializedName("documentation_url")
    private String documentationUrl;

    private List<ApiError> errors;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<RepoDTO> getItems() {
        return items;
    }

    public void setItems(List<RepoDTO> items) {
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
