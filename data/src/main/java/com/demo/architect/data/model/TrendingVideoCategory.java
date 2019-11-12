package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrendingVideoCategory implements Serializable {

    public class TrandingVideoCategoryRespond {
        @SerializedName("count")
        @Expose
        private int count;

        @SerializedName("rows")
        @Expose
        private List<TrendingVideoCategory> rows;

        public int getCount() {
            return count;
        }

        public List<TrendingVideoCategory> getRows() {
            return rows;
        }
    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("trending_videos")
    @Expose
    private List<TrendingVideo> trendingVideoList = new ArrayList<>();

    public List<TrendingVideo> getTrendingVideoList() {
        return trendingVideoList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return imageUrl;
    }


}
