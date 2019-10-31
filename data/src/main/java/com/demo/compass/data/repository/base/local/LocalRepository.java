package com.demo.compass.data.repository.base.local;

import com.demo.compass.data.model.offline.MessageModel;

import rx.Observable;

public interface LocalRepository {

    Observable<String> addMessage(MessageModel model);
}
