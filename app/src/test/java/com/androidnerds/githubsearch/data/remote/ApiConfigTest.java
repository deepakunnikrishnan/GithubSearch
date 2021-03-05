package com.androidnerds.githubsearch.data.remote;


import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;


public class ApiConfigTest {

    @Test
    public void testApiConfig() {
        final Class<?> classUnderTest = ApiConfig.class;
        Assertions.assertPojoMethodsFor(classUnderTest)
                .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}