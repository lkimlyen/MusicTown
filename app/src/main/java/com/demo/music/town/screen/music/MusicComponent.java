package com.demo.music.town.screen.music;



import com.demo.music.town.app.di.ActivityScope;
import com.demo.music.town.screen.dashboard.DashboardActivity;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {MusicModule.class})
public interface MusicComponent {
    void inject(MusicFragment fragment);

}
