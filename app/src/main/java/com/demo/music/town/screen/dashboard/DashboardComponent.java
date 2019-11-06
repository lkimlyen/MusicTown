package com.demo.music.town.screen.dashboard;


import com.demo.music.town.app.di.ActivityScope;
import com.demo.music.town.screen.home.HomeModule;
import com.demo.music.town.screen.music.MusicModule;
import com.demo.music.town.screen.video_local.VideoLocalModule;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class, MusicModule.class, VideoLocalModule.class, DashboardModule.class})
public interface DashboardComponent {
    void inject(DashboardFragment fragment);

}
