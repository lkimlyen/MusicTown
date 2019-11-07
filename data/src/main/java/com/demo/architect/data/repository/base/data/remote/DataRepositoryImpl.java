package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;

public class DataRepositoryImpl implements DataRepository {
    private final static String TAG = DataRepositoryImpl.class.getName();

    private DataApiInterface mRemoteApiInterface;

    public DataRepositoryImpl(DataApiInterface mDataApiInterface) {
        this.mRemoteApiInterface = mDataApiInterface;
    }


    private void handleArtistCategoryResponse(Call<BaseResponse<ArtistCategory.ArtistCategoryRespond>> call, ObservableEmitter<BaseResponse<ArtistCategory.ArtistCategoryRespond>> emitter) {
        try {
            BaseResponse<ArtistCategory.ArtistCategoryRespond> response = call.execute().body();

            if (!emitter.isDisposed()){
                if (response != null) {
                    emitter.onNext(response);
                } else {
                    emitter.onError(new Exception("Network Error!"));
                }
                emitter.onComplete();
            }


        } catch (Exception e) {
            if (!emitter.isDisposed()){
                emitter.onError(e);
                emitter.onComplete();
            }


        }
    }



    @Override
    public Observable<BaseResponse<ArtistCategory.ArtistCategoryRespond>> sendReqestGetArtistCategory(final int limit, final int page) {
        return Observable.create(new ObservableOnSubscribe<BaseResponse<ArtistCategory.ArtistCategoryRespond>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<ArtistCategory.ArtistCategoryRespond>> emitter) throws Exception {
                handleArtistCategoryResponse(mRemoteApiInterface.sendReqestGetArtistCategory(
                        limit, page), emitter);
            }
        });
    }
}
