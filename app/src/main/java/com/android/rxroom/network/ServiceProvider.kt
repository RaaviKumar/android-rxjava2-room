package com.android.rxroom.network


object ServiceProvider {

    private var productApiService: ProductApiService? = null

    fun getProductService() : ProductApiService {
        if(productApiService == null){
            return RetrofitFactory.createService(ProductApiService::class.java)
        }
        return productApiService!!

    }


}
