package com.androidnerds.githubsearch.data.remote;

import com.androidnerds.githubsearch.core.annotation.ErrorType;
import com.androidnerds.githubsearch.data.remote.dto.ApiErrorResponse;
import com.androidnerds.githubsearch.data.remote.dto.GithubSearchResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApiService {

    @GET("search/repositories")
    @ErrorType(ApiErrorResponse.class)
    Single<GithubSearchResponse> getTopRepositories(@Query("q") String query,
                                                    @Query("sort") String sortBy,
                                                    @Query("order") String order,
                                                    @Query("per_page") int limit);
}
