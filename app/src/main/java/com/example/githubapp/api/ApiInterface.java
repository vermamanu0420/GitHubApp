package com.example.githubapp.api;

import com.example.githubapp.model.FollowModel;
import com.example.githubapp.model.GitHubUserDetailModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("users/{userName}")
    Single<GitHubUserDetailModel> getGitHubUser (
            @Path("userName") String searchTerm);

    @GET("users/{userName}/followers")
    Single<List<FollowModel>> getUserFollowersList (
            @Path("userName") String searchTerm);

    @GET("users/{userName}/following")
    Single<List<FollowModel>> getUserFollowingList (
            @Path("userName") String searchTerm);
}
