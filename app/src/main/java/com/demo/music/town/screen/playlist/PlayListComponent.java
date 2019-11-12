package com.demo.music.town.screen.playlist;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {PlayListModule.class})
public interface PlayListComponent {
    void inject(PlayListActivity activity);

}
