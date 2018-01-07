package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by nicks on 1/6/2018.
 */

public class JokeIdlingResource implements IdlingResource {

    @Nullable private ResourceCallback callback;

    private AtomicBoolean isIdling = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdling.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean idling) {
        isIdling.set(idling);
        if (idling && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}
