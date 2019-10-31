package com.demo.compass.domain;

import android.util.Log;

import com.demo.compass.data.model.BaseResponse;
import com.demo.compass.data.model.UserEntity;
import com.demo.compass.data.repository.base.account.remote.AccountRepository;

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
        String deviceToken = ((RequestValue) requestValues).deviceToken;
        String deviceId = ((RequestValue) requestValues).deviceId;
        return remoteRepository.login("ids", "sp", userName, password, deviceToken, deviceId);
    }

    @Override
    protected DisposableObserver<BaseResponse<UserEntity>> buildUseCaseSubscriber() {
        return new DefaultObserver<BaseResponse<UserEntity>>() {
            @Override
            public void onNext(BaseResponse<UserEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data));
                if (useCaseCallback != null) {
                    UserEntity entity = data.getData();
                    if (entity != null && data.getStatus() ==1) {
                        useCaseCallback.onSuccess(new ResponseValue(entity));
                    } else {
                        useCaseCallback.onError(new ErrorValue(data.getDescription()));
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
                    useCaseCallback.onError(new ErrorValue(e.getMessage()));
                }
            }
        };
    }


    public static final class RequestValue implements RequestValues {
        public final String userName;
        public final String password;
        private final String deviceToken;
        private final String deviceId;

        public RequestValue(String userName, String password, String deviceToken, String deviceId) {
            this.userName = userName;
            this.password = password;
            this.deviceToken = deviceToken;
            this.deviceId = deviceId;
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
        private final String description;

        public ErrorValue(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
