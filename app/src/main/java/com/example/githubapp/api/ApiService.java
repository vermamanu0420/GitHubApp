package com.example.githubapp.api;

import com.example.githubapp.dependencyinjection.DaggerApiComponent;
import com.example.githubapp.model.FollowModel;
import com.example.githubapp.model.GitHubUserDetailModel;
import com.example.githubapp.view.GitHubUserDetailFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiService {
    private static ApiService instance;

    @Inject
    public ApiInterface api;

    private ApiService() {
        DaggerApiComponent.create().inject(this);
    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public Single<GitHubUserDetailModel> getGitHubUser(String searchString) {
        return api.getGitHubUser(searchString);
    }

    public Single<List<FollowModel>>getUserFollowersList(String searchString) {
        return api.getUserFollowersList(searchString);
    }

    public Single<List<FollowModel>>getUserFollowingList(String searchString) {
        return api.getUserFollowingList(searchString);
    }
}
