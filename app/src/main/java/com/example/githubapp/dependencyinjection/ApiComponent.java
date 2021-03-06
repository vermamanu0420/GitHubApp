package com.example.githubapp.dependencyinjection;

import com.example.githubapp.api.ApiService;
import com.example.githubapp.viewmodel.GitHubUserViewModel;

import dagger.Component;

@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(ApiService service);
    void inject(GitHubUserViewModel viewModel);

}
