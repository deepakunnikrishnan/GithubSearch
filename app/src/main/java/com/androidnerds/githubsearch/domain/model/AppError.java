package com.androidnerds.githubsearch.domain.model;

public class AppError {

    public enum ErrorType {
        NETWORK_ERROR,
        API_ERROR
    }

    private ErrorType errorType;
    private String message;

    public AppError(ErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
