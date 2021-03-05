package com.androidnerds.githubsearch.core.mapper;

public interface Mapper<I,O> {
    O map(I input);
}
