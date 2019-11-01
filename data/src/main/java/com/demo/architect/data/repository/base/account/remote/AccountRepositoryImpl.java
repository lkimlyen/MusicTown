package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import io.reactivex.Observable;

public class AccountRepositoryImpl implements AccountRepository {
    private final static String TAG = AccountRepositoryImpl.class.getName();

    private AccountApiInterface mRemoteApiInterface;

    public AccountRepositoryImpl(AccountApiInterface mAccountApiInterface) {
        this.mRemoteApiInterface = mAccountApiInterface;
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
    public Observable<BaseResponse<UserEntity>> login(final String appCode, final String userType, final String username,
                                                      final String password, final String deviceToken, final String deviceId) {
        return Observable.create(new ObservableOnSubscribe<BaseResponse<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<UserEntity>> emitter) throws Exception {
                handleUserLoginResponse(mRemoteApiInterface.login(appCode, userType,
                        username, password, deviceToken, deviceId), emitter);
            }
        });
    }
}
