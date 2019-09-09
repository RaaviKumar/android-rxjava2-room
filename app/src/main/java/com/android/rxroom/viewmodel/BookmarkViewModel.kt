package com.android.rxroom.viewmodel

import androidx.lifecycle.ViewModel
import com.android.rxroom.database.ProductDatabase
import com.android.rxroom.manager.ProductManager
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.network.ServiceProvider
import com.android.rxroom.network.UrlProvider
import com.android.rxroom.threadexecutors.AppExecutors
import com.android.rxroom.util.Constant
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
class BookmarkViewModel : ViewModel() {


    fun getBookmarkedProducts(): Observable<List<ProductEntity>>{
        return ProductManager.getBookmarkedProducts()
    }

    fun addRemoveBookmark(productEntity: ProductEntity) {
        ProductManager.addRemoveBookmark(productEntity)
    }

}