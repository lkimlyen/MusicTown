package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.Observable;


public interface AccountRepository {
    Observable<BaseResponse<UserEntity>> login(String username, String password);

}
