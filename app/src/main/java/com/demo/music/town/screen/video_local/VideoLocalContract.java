package com.demo.music.town.screen.video_local;


import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface VideoLocalContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);

        void loginSuccess();

    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }
}
