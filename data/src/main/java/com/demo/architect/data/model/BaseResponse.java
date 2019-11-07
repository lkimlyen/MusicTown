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

    @SerializedName("results")
    @Expose
    private BaseObject<T> results;

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public int getStatus() {
        return status;
    }

    public BaseObject<T> getResults() {
        return results;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
