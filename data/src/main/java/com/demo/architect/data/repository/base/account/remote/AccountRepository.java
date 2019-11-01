package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.Observable;


public interface AccountRepository {
    Observable<BaseResponse<UserEntity>> login(String appCode, String userType, String username, String password, String deviceToken, String deviceId);

}
