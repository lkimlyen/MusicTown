package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArtistCategory implements Serializable {

    public class ArtistCategoryRespond{
        @SerializedName("count")
        @Expose
        private int count;

        @SerializedName("rows")
        @Expose
        private List<ArtistCategory> rows;

        public int getCount() {
            return count;
        }

        public List<ArtistCategory> getRows() {
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
