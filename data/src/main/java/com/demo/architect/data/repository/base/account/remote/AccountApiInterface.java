package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountApiInterface {
    @FormUrlEncoded
    @POST("http://blue.imark.com.vn/WS/api/LoginWS")
    Call<BaseListResponse<UserEntity>> login(
                                         @Field("pUserName") String userName,
                                         @Field("pPassWord") String password);
}
