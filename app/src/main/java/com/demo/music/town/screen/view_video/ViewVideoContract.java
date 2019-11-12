package com.demo.music.town.screen.view_video;


import com.demo.architect.data.model.Comment;
import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface ViewVideoContract {
    interface View extends BaseView<Presenter> {

        void displayListComment(List<Comment> list, int page);
    }

    interface Presenter extends BasePresenter {

        void sendRequestGetListComment(String videoId, int page);
    }
}
