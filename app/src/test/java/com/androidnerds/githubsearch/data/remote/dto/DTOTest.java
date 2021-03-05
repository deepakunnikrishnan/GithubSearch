package com.androidnerds.githubsearch.data.remote.dto;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class DTOTest {

    @Test
    public void testGithubSearchResponse() {
        final Class<?> classUnderTest = GithubSearchResponse.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void testApiErrorResponse() {
        final Class<?> classUnderTest = ApiErrorResponse.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void testRepoDTO() {
        final Class<?> classUnderTest = RepoDTO.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void testApiError() {
        final Class<?> classUnderTest = ApiError.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}