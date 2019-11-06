package com.demo.music.town.screen.video_online;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {VideoOnlineModule.class})
public interface VideoOnlineComponent {
    void inject(VideoOnlineActivity activity);

}
