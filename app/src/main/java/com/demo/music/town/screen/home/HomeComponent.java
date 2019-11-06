package com.demo.music.town.screen.home;



import com.demo.music.town.app.di.ActivityScope;
import com.demo.music.town.screen.dashboard.DashboardActivity;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment fragment);

}
