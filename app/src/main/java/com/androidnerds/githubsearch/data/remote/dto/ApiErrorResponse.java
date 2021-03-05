package com.androidnerds.githubsearch.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiErrorResponse {

    private String message;
    private List<ApiError> errors;
    @SerializedName("documentation_url")
    private String documentationUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
}
