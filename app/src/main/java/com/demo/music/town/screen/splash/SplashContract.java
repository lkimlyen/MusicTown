package com.demo.music.town.screen.splash;


import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);


        void goToDashboard();
    }

    interface Presenter extends BasePresenter {
        void loginAdmin();
    }
}
