package com.apps65.mvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Vitaly on 20.12.2017.
 */
open class BaseMvpPresenter<V: MvpView>: MvpBasePresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}