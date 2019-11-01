package com.demo.music.town.app.di.module;


import com.demo.architect.data.repository.base.account.remote.AccountRepository;
import com.demo.architect.domain.LoginUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by uyminhduc on 12/16/16.
 */
@Module
public class UseCaseModule {
    public UseCaseModule() {
    }

    @Provides
    LoginUseCase provideLoginUseCase(AccountRepository remoteRepository) {
        return new LoginUseCase(remoteRepository);
    }


}

