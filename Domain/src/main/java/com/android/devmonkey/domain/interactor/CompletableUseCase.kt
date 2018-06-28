package com.android.devmonkey.domain.interactor

import com.android.devmonkey.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params?) {
        val completable = buildUseCaseCompletable(params)
                .observeOn(postExecutionThread.scheduler)
                .subscribeOn(Schedulers.computation())
        addDisposable(completable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}