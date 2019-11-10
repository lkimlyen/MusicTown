package com.demo.music.town.screen.tranding_video;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {TrandingVideoModule.class})
public interface TrandingVideoComponent {
    void inject(TrandingVideoActivity activity);

}
