package com.demo.music.town.screen.view_video;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {ViewVideoModule.class})
public interface ViewVideoComponent {
    void inject(ViewVideoActivity activity);

}
