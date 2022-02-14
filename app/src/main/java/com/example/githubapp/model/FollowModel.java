package com.example.githubapp.model;

import com.google.gson.annotations.SerializedName;

public class FollowModel {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private Integer id;
    
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
