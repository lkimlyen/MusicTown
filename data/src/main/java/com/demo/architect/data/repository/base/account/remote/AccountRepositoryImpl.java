package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseListResponse;
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


    private void handleUserLoginResponse(Call<BaseListResponse<UserEntity>> call, ObservableEmitter<BaseListResponse<UserEntity>> emitter) {
        try {
            BaseListResponse<UserEntity> response = call.execute().body();

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
    public Observable<BaseListResponse<UserEntity>> login(final String username,
                                                          final String password) {
        return Observable.create(new ObservableOnSubscribe<BaseListResponse<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseListResponse<UserEntity>> emitter) throws Exception {
                handleUserLoginResponse(mRemoteApiInterface.login(
                        username, password), emitter);
            }
        });
    }
}
