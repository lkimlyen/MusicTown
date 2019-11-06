package com.demo.music.town.manager;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.music.town.app.CoreApplication;

public class TokenManager {

    private String token = null;
    private static TokenManager instance;

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void setToken(String token){
        this.token = token;
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushToken(token);
    }

    public String getToken(){
        if (token == null){
            token = SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getToken();
        }

        return token;
    }

}
