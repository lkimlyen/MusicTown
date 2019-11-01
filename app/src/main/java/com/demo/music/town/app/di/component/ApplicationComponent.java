package com.demo.music.town.app.di.component;



import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseActivity;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.module.ApplicationModule;
import com.demo.music.town.app.di.module.NetModule;
import com.demo.music.town.app.di.module.RepositoryModule;
import com.demo.music.town.app.di.module.UseCaseModule;

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

   // LoginComponent plus(LoginModule module);
}
