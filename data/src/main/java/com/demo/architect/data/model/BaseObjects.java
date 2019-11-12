package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseObjects<T>{

    @SerializedName("objects")
    @Expose
    private T objects;

    public T getObjects() {
        return objects;
    }
}
