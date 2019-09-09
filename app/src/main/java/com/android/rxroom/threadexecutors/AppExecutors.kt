package com.android.rxroom.threadexecutors

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors internal constructor(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {

    constructor() : this(Executors.newCachedThreadPool(), Executors.newFixedThreadPool(THREAD_COUNT), MainThreadExecutor())

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(@NonNull command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {

        private const val THREAD_COUNT = 5
    }
}
