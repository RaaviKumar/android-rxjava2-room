package com.android.rxroom.activity

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
 abstract class BaseActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    fun addSubscription(disposable: Disposable?){
        compositeDisposable.add(disposable!!)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }


}