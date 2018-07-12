package com.imakeanapp.lifecyclesample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import java.util.concurrent.Executor

class DatabaseRepository(private val executor: Executor,
                         private val lifecycle: Lifecycle): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        // Do some initialization
    }

    fun getUser(callback: OnGetUserCallback) {
        executor.execute {
            Thread.sleep(10000)
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                callback.onGetUser("User Object")
            } else {
                Log.d("Lifecycle", "Lifecycle is not at least started")
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // Do some clean up, release resources, etc.
    }
}