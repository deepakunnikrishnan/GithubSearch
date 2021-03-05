package com.androidnerds.githubsearch.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorType {
    Class<?> value();

}