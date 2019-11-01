package com.demo.music.town.screen.dashboard;



import com.demo.music.town.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {DashboardModule.class})
public interface DashboardComponent {
    void inject(DashboardActivity activity);

}
