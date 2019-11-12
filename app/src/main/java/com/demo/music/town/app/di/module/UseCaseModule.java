package com.demo.music.town.app.di.module;


import com.demo.architect.data.repository.base.account.remote.AccountRepository;
import com.demo.architect.data.repository.base.data.remote.DataRepository;
import com.demo.architect.domain.GetArtistCategoryListUseCase;
import com.demo.architect.domain.GetCommentListUseCase;
import com.demo.architect.domain.GetTrandingVideoCategoryListUseCase;
import com.demo.architect.domain.GetTrandingVideoListUseCase;
import com.demo.architect.domain.GetVideoListByCategoryIdUseCase;
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

    @Provides
    GetArtistCategoryListUseCase provideGetArtistCategoryListUseCase(DataRepository remoteRepository) {
        return new GetArtistCategoryListUseCase(remoteRepository);
    }
    @Provides
    GetTrandingVideoListUseCase provideGetTrandingVideoListUseCase(DataRepository remoteRepository) {
        return new GetTrandingVideoListUseCase(remoteRepository);
    }

    @Provides
    GetCommentListUseCase provideGetCommentListUseCase(DataRepository remoteRepository) {
        return new GetCommentListUseCase(remoteRepository);
    }
    @Provides
    GetTrandingVideoCategoryListUseCase provideGetTrandingVideoCategoryListUseCase(DataRepository remoteRepository) {
        return new GetTrandingVideoCategoryListUseCase(remoteRepository);
    }

    @Provides
    GetVideoListByCategoryIdUseCase provideGetVideoListByCategoryIdUseCase(DataRepository remoteRepository) {
        return new GetVideoListByCategoryIdUseCase(remoteRepository);
    }
}

