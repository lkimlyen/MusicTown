package com.demo.music.town.app.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by admin on 5/18/17.
 */

public class MainThreadBus extends Bus {
    private final Handler handler = new Handler(Looper.getMainLooper());

    public MainThreadBus(ThreadEnforcer enforcer) {
        super(enforcer);
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MainThreadBus.super.post(event);
                }
            });
        }
    }
}
