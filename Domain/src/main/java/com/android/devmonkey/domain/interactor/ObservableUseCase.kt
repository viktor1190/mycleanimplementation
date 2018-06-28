package com.android.devmonkey.domain.interactor

import com.android.devmonkey.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCase(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params?) {
        val observable = buildUseCase(params)
                .observeOn(postExecutionThread.scheduler)
                .subscribeOn(Schedulers.computation())
        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}