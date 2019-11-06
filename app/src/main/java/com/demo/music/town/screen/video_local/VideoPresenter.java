package com.demo.music.town.screen.video_local;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.LoginUseCase;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class VideoPresenter implements VideoLocalContract.Presenter {

    private final String TAG = VideoPresenter.class.getName();
    private final VideoLocalContract.View view;
    @Inject
    LocalRepository localRepository;

    private final LoginUseCase loginUsecase;

    @Inject
    VideoPresenter(@NonNull VideoLocalContract.View view, LoginUseCase loginUsecase) {
        this.view = view;
        this.loginUsecase = loginUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void login(String username, String password) {
        view.showProgressBar();

        loginUsecase.executeIO(new LoginUseCase.RequestValue(username, password), new BaseUseCase.UseCaseCallback
                <LoginUseCase.ResponseValue, LoginUseCase.ErrorValue>() {
            @Override
            public void onSuccess(LoginUseCase.ResponseValue successResponse) {
                view.hideProgressBar();
                Log.d(TAG, new Gson().toJson(successResponse.getEntity()));
                view.showSuccess("Thành công");
            }

            @Override
            public void onError(LoginUseCase.ErrorValue errorResponse) {

                    //view.showError(errorResponse.getDescription());
                view.hideProgressBar();
            }
        });
    }

}
