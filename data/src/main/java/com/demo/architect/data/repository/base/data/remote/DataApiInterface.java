package com.demo.architect.data.repository.base.data.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataApiInterface {
    @FormUrlEncoded
    @POST("http://blue.imark.com.vn/WS/api/LoginWS")
    Call<BaseResponse<UserEntity>> login(
            @Field("pUserName") String userName,
            @Field("pPassWord") String password);
}
