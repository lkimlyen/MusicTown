package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.model.TrendingVideoCategory;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class DataRepositoryImpl implements DataRepository {
    private final static String TAG = DataRepositoryImpl.class.getName();

    private DataApiInterface mRemoteApiInterface;

    public DataRepositoryImpl(DataApiInterface mDataApiInterface) {
        this.mRemoteApiInterface = mDataApiInterface;
    }


    private void handleArtistCategoryResponse(Call<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> call, ObservableEmitter<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> emitter) {
        try {
            BaseListResponse<ArtistCategory.ArtistCategoryRespond> response = call.execute().body();

            if (!emitter.isDisposed()) {
                if (response != null) {
                    emitter.onNext(response);
                } else {
                    emitter.onError(new Exception("Network Error!"));
                }
                emitter.onComplete();
            }


        } catch (Exception e) {
            if (!emitter.isDisposed()) {
                emitter.onError(e);
                emitter.onComplete();
            }


        }
    }

    private void handleTrandingVideoResponse(Call<BaseListResponse<TrendingVideo.TrandingVideoRespond>> call,
                                             ObservableEmitter<BaseListResponse<TrendingVideo.TrandingVideoRespond>> emitter) {
        try {
            BaseListResponse<TrendingVideo.TrandingVideoRespond> response = call.execute().body();

            if (!emitter.isDisposed()) {
                if (response != null) {
                    emitter.onNext(response);
                } else {
                    emitter.onError(new Exception("Network Error!"));
                }
                emitter.onComplete();
            }


        } catch (Exception e) {
            if (!emitter.isDisposed()) {
                emitter.onError(e);
                emitter.onComplete();
            }


        }
    }

    private void handleTrandingVideoCategoryResponse(Call<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> call,
                                                     ObservableEmitter<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> emitter) {
        try {
            BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond> response = call.execute().body();

            if (!emitter.isDisposed()) {
                if (response != null) {
                    emitter.onNext(response);
                } else {
                    emitter.onError(new Exception("Network Error!"));
                }
                emitter.onComplete();
            }


        } catch (Exception e) {
            if (!emitter.isDisposed()) {
                emitter.onError(e);
                emitter.onComplete();
            }


        }
    }

    private void handleCommentResponse(Call<BaseResponse<Comment.CommentListRespond>> call,
                                       ObservableEmitter<BaseResponse<Comment.CommentListRespond>> emitter) {
        try {
            BaseResponse<Comment.CommentListRespond> response = call.execute().body();

            if (!emitter.isDisposed()) {
                if (response != null) {
                    emitter.onNext(response);
                } else {
                    emitter.onError(new Exception("Network Error!"));
                }
                emitter.onComplete();
            }


        } catch (Exception e) {
            if (!emitter.isDisposed()) {
                emitter.onError(e);
                emitter.onComplete();
            }


        }
    }

    @Override
    public Observable<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(final int limit, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseListResponse<ArtistCategory.ArtistCategoryRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseListResponse<ArtistCategory.ArtistCategoryRespond>> emitter) throws Exception {
                handleArtistCategoryResponse(mRemoteApiInterface.sendReqestGetArtistCategory(
                        limit, page), emitter);
            }
        });
    }

    @Override
    public Observable<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetTrandingVideo(final int limit, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseListResponse<TrendingVideo.TrandingVideoRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseListResponse<TrendingVideo.TrandingVideoRespond>> emitter) throws Exception {
                handleTrandingVideoResponse(mRemoteApiInterface.sendReqestGetTrandingVideo(
                        limit, page), emitter);
            }
        });
    }

    @Override
    public Observable<BaseResponse<Comment.CommentListRespond>> sendRequestGetListComment(final Map<String, Object> params, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseResponse<Comment.CommentListRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<Comment.CommentListRespond>> emitter) throws Exception {
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
                handleCommentResponse(mRemoteApiInterface.sendRequestGetListComment(body, page), emitter);
            }
        });
    }

    @Override
    public Observable<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> sendRequestGetTrendingCategory(final String filter, final int limit, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseListResponse<TrendingVideoCategory.TrandingVideoCategoryRespond>> emitter) throws Exception {
                handleTrandingVideoCategoryResponse(mRemoteApiInterface.sendRequestGetTrendingCategory(filter,
                        limit, page), emitter);
            }
        });
    }

    @Override
    public Observable<BaseListResponse<TrendingVideo.TrandingVideoRespond>> sendReqestGetVideoByCategoryId(final String filter, final int limit, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseListResponse<TrendingVideo.TrandingVideoRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseListResponse<TrendingVideo.TrandingVideoRespond>> emitter) throws Exception {
                handleTrandingVideoResponse(mRemoteApiInterface.sendReqestGetVideoByCategoryId(
                        filter, limit, page), emitter);
            }
        });
    }
}
