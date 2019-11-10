package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrendingVideo {

    public class TrandingVideoRespond {
        @SerializedName("count")
        @Expose
        private int count;

        @SerializedName("rows")
        @Expose
        private List<TrendingVideo> rows;

        public int getCount() {
            return count;
        }

        public List<TrendingVideo> getRows() {
            return rows;
        }
    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;

    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    @SerializedName("duration")
    @Expose
    private long duration;

    public String getVideoUrl() {
        return videoUrl;
    }

    public long getDuration() {
        return duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
