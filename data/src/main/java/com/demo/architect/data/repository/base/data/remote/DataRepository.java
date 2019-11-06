package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.Observable;


public interface DataRepository {
    Observable<BaseResponse<UserEntity>> login(String username, String password);

}
