package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.UserEntity;

import io.reactivex.Observable;


public interface AccountRepository {
    Observable<BaseListResponse<UserEntity>> login(String username, String password);

}
