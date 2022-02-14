package com.example.githubapp.dependencyinjection;

import com.example.githubapp.api.ApiInterface;
import com.example.githubapp.api.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public static final String BASE_URL ="https://api.github.com/";

    @Provides
    public ApiInterface provideGithubApi(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);

    }

    @Provides
    public ApiService provideGithubApiService(){
        return  ApiService.getInstance();
    }
}
