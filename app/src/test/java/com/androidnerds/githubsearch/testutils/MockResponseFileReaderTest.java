package com.androidnerds.githubsearch.testutils;


import android.text.TextUtils;


import com.androidnerds.githubsearch.data.remote.ApiConstants;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class MockResponseFileReaderTest {

    @Test
    public void testReadFile() {
        MockResponseFileReader fileReader = new MockResponseFileReader(ApiConstants.TOP_REPOSITORIES);
        assertFalse(TextUtils.isEmpty(fileReader.getContent()));
    }
}