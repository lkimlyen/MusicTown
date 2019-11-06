package com.demo.music.town.screen.splash;


import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class SplashModule {
    private final SplashContract.View SplashContractView;

    public SplashModule(SplashContract.View SplashContractView) {
        this.SplashContractView = SplashContractView;
    }

    @Provides
    @NonNull
    SplashContract.View provideSplashContractView() {
        return this.SplashContractView;
    }
}

