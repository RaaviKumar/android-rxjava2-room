package com.android.rxroom.manager

import com.android.rxroom.database.ProductDatabase
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.network.ServiceProvider
import com.android.rxroom.network.UrlProvider
import com.android.rxroom.threadexecutors.AppExecutors
import com.android.rxroom.util.Constant
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ravisahani on 09,September,2019
RxRoom

 */
object ProductManager {

     fun getProducts(): Observable<List<ProductEntity>> {
        return Observable.create { observer ->

            ServiceProvider.getProductService().getProducts(UrlProvider.getProducts())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<List<ProductEntity>> {

                    var disposible: Disposable? = null

                    override fun onComplete() {
                        disposible?.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposible = d
                    }

                    override fun onNext(productList: List<ProductEntity>) {
                        observer.onNext(productList)
                        AppExecutors().diskIO().execute {
                            ProductDatabase.getInstance().productDao().addProductList(productList)
                        }
                        observer.onComplete()

                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }

                })

        }
    }

    fun getProductList() : Observable<List<ProductEntity>>{
       return ProductDatabase.getInstance().productDao().getProductList()
    }

    fun addRemoveBookmark(productEntity: ProductEntity){
        AppExecutors().diskIO().execute {
            if (productEntity.bookmarkStatus == Constant.BOOKMARK_ADDED_STATUS) {
                productEntity.bookmarkStatus = Constant.BOOKMARK_REMOVED_STATUS
                ProductDatabase.getInstance().productDao().addRemoveBookmark(productEntity)
            } else {
                productEntity.bookmarkStatus = Constant.BOOKMARK_ADDED_STATUS
                ProductDatabase.getInstance().productDao().addRemoveBookmark(productEntity)
            }

        }
    }

    fun getBookmarkedProducts(): Observable<List<ProductEntity>>{
        return ProductDatabase.getInstance().productDao().getBookmarkedProducts()
    }


}