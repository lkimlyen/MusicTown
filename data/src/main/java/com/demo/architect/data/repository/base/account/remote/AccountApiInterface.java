package com.demo.architect.data.repository.base.account.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountApiInterface {
    @FormUrlEncoded
    @POST("http://blue.imark.com.vn/WS/api/LoginWS")
    Call<BaseResponse<UserEntity>> login(@Field("pAppCode") String appCode,
                                         @Field("pUserType") String userType,
                                         @Field("pUserName") String userName,
                                         @Field("pPassWord") String password,
                                         @Field("pDeviceToken") String deviceToken,
                                         @Field("pDeviceID") String deviceId);
}
