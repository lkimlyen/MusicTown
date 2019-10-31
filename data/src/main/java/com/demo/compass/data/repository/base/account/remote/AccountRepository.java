package com.demo.compass.data.repository.base.account.remote;

import com.demo.compass.data.model.BaseResponse;
import com.demo.compass.data.model.UserEntity;

import io.reactivex.Observable;


public interface AccountRepository {
    Observable<BaseResponse<UserEntity>> login(String appCode, String userType, String username, String password, String deviceToken, String deviceId);

}
