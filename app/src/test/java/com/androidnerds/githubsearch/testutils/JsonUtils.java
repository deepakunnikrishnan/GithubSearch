package com.androidnerds.githubsearch.testutils;

import com.google.gson.Gson;

public class JsonUtils {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
