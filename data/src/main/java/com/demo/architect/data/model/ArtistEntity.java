package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistEntity {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("artist_category_id")
    @Expose
    private String artistCategoryId;

}
