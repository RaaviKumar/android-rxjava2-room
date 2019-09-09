package com.android.rxroom.network

import com.android.rxroom.model.ProductEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
interface ProductApiService {

    @GET
    fun getProducts(@Url url : String): Observable<List<ProductEntity>>


}