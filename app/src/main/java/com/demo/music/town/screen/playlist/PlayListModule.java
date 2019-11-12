package com.demo.music.town.screen.playlist;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class PlayListModule {
    private final PlayListContract.View VideoContractView;

    public PlayListModule(PlayListContract.View VideoContractView) {
        this.VideoContractView = VideoContractView;
    }

    @Provides
    @NonNull
    PlayListContract.View provideVideoContractView() {
        return this.VideoContractView;
    }
}

