package com.demo.music.town.screen.video_local;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class VideoLocalModule {
    private final VideoLocalContract.View VideoContractView;

    public VideoLocalModule(VideoLocalContract.View VideoContractView) {
        this.VideoContractView = VideoContractView;
    }

    @Provides
    @NonNull
    VideoLocalContract.View provideVideoContractView() {
        return this.VideoContractView;
    }
}

