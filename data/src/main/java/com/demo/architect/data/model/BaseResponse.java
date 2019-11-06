package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uyminhduc on 10/23/16.
 */

public class BaseResponse<T> {
    @SerializedName("code")
    @Expose
    private int status;


    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("results")
    @Expose
    private T data;

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public T getData() {
        return data;
    }
}
