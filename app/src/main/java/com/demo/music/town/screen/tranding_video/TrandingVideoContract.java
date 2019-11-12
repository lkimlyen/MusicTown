package com.demo.music.town.screen.tranding_video;


import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface TrandingVideoContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);

        void displayTrandingVideoList(List<TrendingVideo> list, int page);

        void endLoadingList();

    }

    interface Presenter extends BasePresenter {
        void sendRequestGetTrandingVideoList(String displayType, int page);


    }
}
