package com.demo.music.town.screen.tranding_video;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class TrandingVideoModule {
    private final TrandingVideoContract.View VideoContractView;

    public TrandingVideoModule(TrandingVideoContract.View VideoContractView) {
        this.VideoContractView = VideoContractView;
    }

    @Provides
    @NonNull
    TrandingVideoContract.View provideVideoContractView() {
        return this.VideoContractView;
    }
}

