package com.demo.music.town.screen.home;


import com.demo.architect.data.model.ArtistCategory;
import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        void showError(String error);

        void displayArtistCategoryList(List<ArtistCategory> list);

    }

    interface Presenter extends BasePresenter {
        void sendRequestGetArtistCategory();
    }
}
