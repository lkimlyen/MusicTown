package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.TrendingVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataApiInterface {

    @GET("artist_category?fields=[\"$all\"]")
    Call<BaseResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(
            @Query("limit") int limit,
            @Query("page") int page);

    @GET("trending_video?fields=[\"$all\"]")
    Call<BaseResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetTrandingVideo(
            @Query("limit") int limit,
            @Query("page") int page);
}
