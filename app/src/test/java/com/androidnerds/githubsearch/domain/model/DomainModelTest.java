package com.androidnerds.githubsearch.domain.model;

import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class DomainModelTest {

    @Test
    public void testAppError() {
        final Class<?> classUnderTest = AppError.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void testRepo() {
        final Class<?> classUnderTest = Repo.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void testSearchResult() {
        final Class<?> classUnderTest = SearchResult.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}