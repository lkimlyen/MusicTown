package com.demo.music.town.screen.video_local;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {VideoLocalModule.class})
public interface VideoLocalComponent {
    void inject(VideoLocalFragment fragment);

}
