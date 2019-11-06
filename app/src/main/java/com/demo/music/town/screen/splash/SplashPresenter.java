package com.demo.music.town.screen.splash;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.LoginUseCase;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.manager.TokenManager;
import com.demo.music.town.util.NetworkUtils;

import javax.inject.Inject;


/**
 * Created by MSI on 26/11/2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private final String TAG = SplashPresenter.class.getName();
    private final SplashContract.View view;
    private final LoginUseCase loginUseCase;
    @Inject
    LocalRepository localRepository;

    @Inject
    SplashPresenter(@NonNull SplashContract.View view, LoginUseCase loginUseCase) {
        this.view = view;
        this.loginUseCase = loginUseCase;
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
    public void loginAdmin() {
//        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
//            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
//            return;
//        }
//        loginUseCase.executeIO(new LoginUseCase.RequestValue("master", "c030437f6e8e94d244bc602606df5235"), new BaseUseCase.UseCaseCallback() {
//            @Override
//            public void onSuccess(Object successResponse) {
//                if (successResponse instanceof LoginUseCase.ResponseValue) {
//                    UserEntity userEntity = ((LoginUseCase.ResponseValue) successResponse).getEntity();
//                    TokenManager.getInstance().setToken(userEntity.getToken());
//                    view.goToDashboard();
//                }
//            }
//
//            @Override
//            public void onError(Object errorResponse) {
//                if (errorResponse instanceof LoginUseCase.ErrorValue) {
//
//                }
//
//            }
//        });
    }
}
