package com.demo.music.town.app.di.component;



import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseActivity;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.module.ApplicationModule;
import com.demo.music.town.app.di.module.NetModule;
import com.demo.music.town.app.di.module.RepositoryModule;
import com.demo.music.town.app.di.module.UseCaseModule;
import com.demo.music.town.screen.dashboard.DashboardComponent;
import com.demo.music.town.screen.dashboard.DashboardModule;
import com.demo.music.town.screen.home.HomeModule;
import com.demo.music.town.screen.music.MusicModule;
import com.demo.music.town.screen.splash.SplashComponent;
import com.demo.music.town.screen.splash.SplashModule;
import com.demo.music.town.screen.video_local.VideoLocalComponent;
import com.demo.music.town.screen.video_local.VideoLocalModule;
import com.demo.music.town.screen.video_online.VideoOnlineComponent;
import com.demo.music.town.screen.video_online.VideoOnlineModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by uyminhduc on 12/16/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class,
        NetModule.class,
        UseCaseModule.class,
        RepositoryModule.class})
public interface ApplicationComponent {

    void inject(CoreApplication application);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    DashboardComponent plus(HomeModule homeModule, MusicModule musicModule, VideoLocalModule videoLocalModule, DashboardModule dashboardModule);

     SplashComponent plus(SplashModule module);

     VideoOnlineComponent plus(VideoOnlineModule module);
}
