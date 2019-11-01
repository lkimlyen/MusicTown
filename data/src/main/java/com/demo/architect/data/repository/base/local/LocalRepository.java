package com.demo.architect.data.repository.base.local;

import com.demo.architect.data.model.offline.MessageModel;

import rx.Observable;

public interface LocalRepository {

    Observable<String> addMessage(MessageModel model);
}
