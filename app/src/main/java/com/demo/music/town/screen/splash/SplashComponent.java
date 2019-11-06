package com.demo.music.town.screen.splash;


import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity activity);

}
