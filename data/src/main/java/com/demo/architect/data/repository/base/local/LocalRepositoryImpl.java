package com.demo.architect.data.repository.base.local;

import com.demo.architect.data.model.offline.MessageModel;

import rx.Observable;

public class LocalRepositoryImpl implements LocalRepository{
    @Override
    public Observable<String> addMessage(MessageModel model) {

        return null;
    }
}
