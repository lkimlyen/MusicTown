package com.demo.music.town.screen.video_online;


import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface VideoOnlineContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);

        void loginSuccess();

    }

    interface Presenter extends BasePresenter {
      void sendRequestGetListVideo();
    }
}
