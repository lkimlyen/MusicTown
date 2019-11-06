package com.demo.music.town.screen.music;


import com.demo.architect.data.model.SongEntity;
import com.demo.music.town.app.base.BasePresenter;
import com.demo.music.town.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface MusicContract {
    interface View extends BaseView<Presenter> {

        void showListSong(List<SongEntity> songList);

    }

    interface Presenter extends BasePresenter {
        void getListMusic();
    }
}
