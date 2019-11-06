package com.demo.music.town.screen.dashboard;

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

public class DashboardPresenter implements DashboardContract.Presenter {

    private final String TAG = DashboardPresenter.class.getName();
    private final DashboardContract.View view;
    @Inject
    LocalRepository localRepository;

    @Inject
    DashboardPresenter(@NonNull DashboardContract.View view) {
        this.view = view;
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


}
