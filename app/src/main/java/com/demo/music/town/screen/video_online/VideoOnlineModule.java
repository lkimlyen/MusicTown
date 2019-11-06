package com.demo.music.town.screen.video_online;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class VideoOnlineModule {
    private final VideoOnlineContract.View VideoContractView;

    public VideoOnlineModule(VideoOnlineContract.View VideoContractView) {
        this.VideoContractView = VideoContractView;
    }

    @Provides
    @NonNull
    VideoOnlineContract.View provideVideoContractView() {
        return this.VideoContractView;
    }
}

