package com.demo.music.town.screen.view_video;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class ViewVideoModule {
    private final ViewVideoContract.View VideoContractView;

    public ViewVideoModule(ViewVideoContract.View VideoContractView) {
        this.VideoContractView = VideoContractView;
    }

    @Provides
    @NonNull
    ViewVideoContract.View provideVideoContractView() {
        return this.VideoContractView;
    }
}

