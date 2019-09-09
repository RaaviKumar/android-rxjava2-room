package com.android.rxroom.network

import androidx.annotation.NonNull
import com.android.rxroom.BuildConfig
import com.android.rxroom.RxRoomApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal object RetrofitFactory {

    private var retrofit: Retrofit? = null

    private val retrofitClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(UrlConstant.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!
        }

    private val gson: Gson
        @NonNull
        get() {
            return GsonBuilder().create()
        }

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        @NonNull
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return httpLoggingInterceptor
        }



    private val okHttpClient: OkHttpClient
        @NonNull
        get() {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(FakeInterceptor(RxRoomApplication.application))
            builder.addInterceptor(httpLoggingInterceptor)
            builder.connectTimeout(60, TimeUnit.SECONDS) // connect timeout
            builder.readTimeout(60, TimeUnit.SECONDS)    // socket timeout
            builder.retryOnConnectionFailure(true)
            return builder.build()
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitClient.create(serviceClass)
    }
}
