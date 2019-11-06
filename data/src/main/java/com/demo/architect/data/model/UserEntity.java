package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserEntity implements Serializable {

    public static class User {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("fullname")
        @Expose
        private String fullName;

        @SerializedName("avatar")
        @Expose
        private String avatar;

        @SerializedName("phone")
        @Expose
        private String phone;

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private String username;


    }

    @SerializedName("object")
    @Expose
    private User user;

    @SerializedName("token")
    @Expose
    private String token;
    public String getId() {
        return user.id;
    }

    public String getFullName() {
        return user.fullName;
    }

    public String getAvatar() {
        return user.avatar;
    }

    public String getPhone() {
        return user.phone;
    }

    public String getEmail() {
        return user.email;
    }

    public String getUsername() {
        return user.username;
    }

    public String getToken() {
        return token;
    }
}