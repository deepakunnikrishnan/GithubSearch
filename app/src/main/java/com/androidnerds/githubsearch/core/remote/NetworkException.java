package com.androidnerds.githubsearch.core.remote;

import androidx.annotation.Nullable;

public class NetworkException extends RuntimeException {

    private final Throwable throwable;
    private final Object error;

    public NetworkException(Throwable throwable, Object error) {
        super(throwable);
        this.throwable = throwable;
        this.error = error;
    }

    @Nullable
    @Override
    public String getMessage() {
        return "";
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public Object getError() {
        return error;
    }
}
