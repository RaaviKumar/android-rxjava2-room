package com.android.rxroom.dao

import androidx.room.*
import com.android.rxroom.database.TableConstant
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.util.Constant
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
@Dao
interface ProductDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun addProductList(product: List<ProductEntity>)

    @Query("SELECT * FROM "+TableConstant.PRODUCT)
    fun getProductList(): Observable<List<ProductEntity>>

    @Update
    fun addRemoveBookmark(product: ProductEntity)


    @Query("SELECT * FROM "+TableConstant.PRODUCT+" where bookmarkStatus = "+Constant.BOOKMARK_ADDED_STATUS)
    fun getBookmarkedProducts(): Observable<List<ProductEntity>>


}