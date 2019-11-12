package com.demo.music.town.screen.playlist;


import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PlayListContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);


        void displayTrendingVideoCategoryList(List<TrendingVideoCategory> list, int page);

        void endLoadingList();
        void displayArtistCategoryList(List<ArtistCategory> list, int page);

    }

    interface Presenter extends BasePresenter {
        void sendRequestGetTrendingCategory(String displayType, int page);

        void sendRequestGetArtistCategory(int page);


    }
}
