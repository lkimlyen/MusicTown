package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("current_page")
    @Expose
    private int currentPage;
    @SerializedName("next_page")
    @Expose
    private int nextPage;
    @SerializedName("prev_page")
    @Expose
    private int prevPage;
    @SerializedName("limit")
    @Expose
    private int limit;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getLimit() {
        return limit;
    }
}
