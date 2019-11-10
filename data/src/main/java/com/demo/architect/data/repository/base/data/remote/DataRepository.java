package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.TrendingVideo;

import io.reactivex.Observable;


public interface DataRepository {
    Observable<BaseResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(int limit, int page);
    Observable<BaseResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetTrandingVideo(int limit, int page);

}
