package com.demo.music.town.screen.music;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class MusicModule {
    private final MusicContract.View MusicContractView;

    public MusicModule(MusicContract.View MusicContractView) {
        this.MusicContractView = MusicContractView;
    }

    @Provides
    @NonNull
    MusicContract.View provideMusicContractView() {
        return this.MusicContractView;
    }
}

