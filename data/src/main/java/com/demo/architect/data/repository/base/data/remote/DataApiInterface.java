package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.model.TrendingVideoCategory;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataApiInterface {

    @GET("artist_category?fields=[\"$all\"]")
    Call<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(
            @Query("limit") int limit,
            @Query("page") int page);

    @GET("trending_video?fields=[\"$all\"]")
    Call<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetTrandingVideo(
            @Query("limit") int limit,
            @Query("page") int page);

    @GET("trending_video?fields=[\"$all\"]")
    Call<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetVideoByCategoryId(
            @Query("filter") String filter,
            @Query("limit") int limit,
            @Query("page") int page);

    @GET("trending_video_category")
    Call<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> sendRequestGetTrendingCategory(
            @Query("fields") String fields,
            @Query("limit") int limit,
            @Query("page") int page);

    @Headers("Content-Type: application/json")
    @POST("trending_video/get_youtube_comment")
    Call<BaseResponse<Comment.CommentListRespond>> sendRequestGetListComment(@Body RequestBody body, @Query("page") int page);

}
