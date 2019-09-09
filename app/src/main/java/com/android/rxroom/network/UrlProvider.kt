package com.android.rxroom.network

import android.net.Uri

/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
object UrlProvider {

    fun getProducts() : String{

        val uri = Uri.parse(UrlConstant.BASE_URL)
            .buildUpon()
            .appendPath("products")
            .build()

//        return uri.toString()  // For API call uncomment this line of code.

        return "//api/product" // It is for mocking api response from the assets folder using FakeInterceptor. For API Call comment this line of code.
    }

}