package com.demo.music.town.screen.home;

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

public class HomePresenter implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;
    @Inject
    LocalRepository localRepository;


    @Inject
    HomePresenter(@NonNull HomeContract.View view) {
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



    @Override
    public void sendRequestGetArtistCategory() {

    }
}
