package com.android.devmonkey.domain.executor

import io.reactivex.Scheduler

// This class help us to separation of concerns between domain layer and Android layer
// because Android UI thread is a framework feature we couldn'' simply add it directly to this module
// so we need to implement this interface in order to delegate the thread execution
interface PostExecutionThread {
    val scheduler: Scheduler
}