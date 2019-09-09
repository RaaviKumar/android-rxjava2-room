package com.android.rxroom

import android.app.Application
import com.android.rxroom.database.ProductDatabase

/**
 * Created by ravisahani on 07,September,2019
RxRoom

 */
class RxRoomApplication : Application() {

    companion object {
        lateinit var application: RxRoomApplication
    }

    override fun onCreate() {
        super.onCreate()
        ProductDatabase.initAppDatabase(this)
        application = this
    }



}