package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseObject<T>{

    @SerializedName("object")
    @Expose
    private T object;

    public T getObjects() {
        return object;
    }
}
