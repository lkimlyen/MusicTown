package com.demo.music.town.app.di.module;



import com.demo.architect.data.repository.base.data.remote.DataRepository;
import com.demo.architect.data.repository.base.data.remote.DataRepositoryImpl;
import com.demo.music.town.app.CoreApplication;
import com.demo.architect.data.repository.base.account.remote.AccountRepository;
import com.demo.architect.data.repository.base.account.remote.AccountRepositoryImpl;
import com.demo.architect.data.repository.base.local.DatabaseRealm;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.data.repository.base.local.LocalRepositoryImpl;
import com.demo.architect.data.repository.base.notification.remote.NotificationRepository;
import com.demo.architect.data.repository.base.notification.remote.NotificationRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public LocalRepository provideMessageRepository() {
        return new LocalRepositoryImpl();
    }

    @Provides
    @Singleton
    public DatabaseRealm provideDatabaseRealm() {
        return new DatabaseRealm(CoreApplication.getInstance());
    }

    @Provides
    @Singleton
    AccountRepository provideAccountRepository(AccountRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    NotificationRepository provideNotificationRepository(NotificationRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }
    @Provides
    @Singleton
    DataRepository provideDataRepository(DataRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

}
