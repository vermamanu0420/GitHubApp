package com.example.githubapp.viewmodel;

import com.example.githubapp.api.ApiService;
import com.example.githubapp.dependencyinjection.DaggerApiComponent;
import com.example.githubapp.model.FollowModel;
import com.example.githubapp.model.GitHubUserDetailModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GitHubUserViewModel extends ViewModel {

    public MutableLiveData<List<FollowModel>> mutableFollowList = new MutableLiveData<List<FollowModel>>();
    public MutableLiveData<Boolean> imageLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    public MutableLiveData<GitHubUserDetailModel> githubUserDetail = new MutableLiveData<GitHubUserDetailModel>();

    @Inject
    public ApiService apiService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public GitHubUserViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void fetchFollowersList(String userName) {
        disposable.add(
                apiService.getUserFollowersList(userName)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<FollowModel>>() {
                            @Override
                            public void onSuccess(@NonNull List<FollowModel> followersList) {
                                imageLoadError.setValue(false);
                                loading.setValue(false);
                                mutableFollowList.setValue(followersList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                imageLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void fetchFollowingList(String userName) {
        disposable.add(
                apiService.getUserFollowersList(userName)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<FollowModel>>() {
                            @Override
                            public void onSuccess(@NonNull List<FollowModel> followingList) {
                                imageLoadError.setValue(false);
                                loading.setValue(false);
                                mutableFollowList.setValue(followingList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                imageLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void fetchUserDetail(String userName) {
        disposable.add(
                apiService.getGitHubUser(userName)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<GitHubUserDetailModel>() {
                            @Override
                            public void onSuccess(@NonNull GitHubUserDetailModel userDetail) {
                                imageLoadError.setValue(false);
                                loading.setValue(false);
                                githubUserDetail.setValue(userDetail);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                imageLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }



}


