package com.demo.architect.domain;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.base.account.remote.AccountRepository;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class LoginUseCase extends BaseUseCase<BaseResponse<UserEntity>> {
    private static final String TAG = LoginUseCase.class.getSimpleName();
    private final AccountRepository remoteRepository;

    public LoginUseCase(AccountRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable<BaseResponse<UserEntity>> buildUseCaseObservable() {
        String userName = ((RequestValue) requestValues).userName;
        String password = ((RequestValue) requestValues).password;
        return remoteRepository.login(userName, password);
    }

    @Override
    protected DisposableObserver<BaseResponse<UserEntity>> buildUseCaseSubscriber() {
        return new DefaultObserver<BaseResponse<UserEntity>>() {
            @Override
            public void onNext(BaseResponse<UserEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data));
                if (useCaseCallback != null) {
                    UserEntity entity = data.getResults().getObjects();
                    if (entity != null && data.getStatus() ==200) {
                        useCaseCallback.onSuccess(new ResponseValue(entity));
                    } else {
                        useCaseCallback.onError(new ErrorValue(data.getStatus()));
                    }
                }

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new ErrorValue(-1));
                }
            }
        };
    }


    public static final class RequestValue implements RequestValues {
        public final String userName;
        public final String password;

        public RequestValue(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private UserEntity entity;

        public ResponseValue(UserEntity entity) {
            this.entity = entity;
        }

        public UserEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
        private final int error;

        public ErrorValue(int error) {
            this.error = error;
        }

        public int getData() {
            return error;
        }
    }
}
