package com.demo.architect.data.repository.base.data.remote;

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


    private void handleUserLoginResponse(Call<BaseResponse<UserEntity>> call, ObservableEmitter<BaseResponse<UserEntity>> emitter) {
        try {
            BaseResponse<UserEntity> response = call.execute().body();

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
    public Observable<BaseResponse<UserEntity>> login( final String username,
                                                      final String password) {
        return Observable.create(new ObservableOnSubscribe<BaseResponse<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<UserEntity>> emitter) throws Exception {
                handleUserLoginResponse(mRemoteApiInterface.login(
                        username, password), emitter);
            }
        });
    }
}
