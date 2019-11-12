package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.model.TrendingVideoCategory;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Query;


public interface DataRepository {
    Observable<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(int limit, int page);
    Observable<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetTrandingVideo(int limit, int page);
    Observable<BaseResponse<Comment.CommentListRespond>> sendRequestGetListComment(final Map<String, Object> params, final int page);

    Observable<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> sendRequestGetTrendingCategory(final String filter, final int limit, final int page);
    Observable<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetVideoByCategoryId(
        String filter,
            int limit, int page);

}
