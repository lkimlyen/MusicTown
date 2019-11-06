package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoEntity {

    public class VideoListRespond {


        @SerializedName("rows")
        @Expose
        private List<VideoEntity> videoList = new ArrayList<>();


    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;

    public String getId() {
        return id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
