package com.demo.music.town.screen.music;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.model.SongEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.LoginUseCase;
import com.demo.music.town.app.CoreApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class MusicPresenter implements MusicContract.Presenter {

    private final String TAG = MusicPresenter.class.getName();
    private final MusicContract.View view;
    @Inject
    LocalRepository localRepository;

    private final LoginUseCase loginUsecase;

    @Inject
    MusicPresenter(@NonNull MusicContract.View view, LoginUseCase loginUsecase) {
        this.view = view;
        this.loginUsecase = loginUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public void getListMusic() {
        ContentResolver contentResolver = CoreApplication.getInstance().getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);
        List<SongEntity> songList = new ArrayList<>();
        while (cursor.moveToNext()) {
            SongEntity song = new SongEntity(cursor.getInt(0), cursor.getString(2), cursor.getString(1), cursor.getColumnName(4), cursor.getLong(5));
            songList.add(song);
        }

        view.showListSong(songList);
    }
}
