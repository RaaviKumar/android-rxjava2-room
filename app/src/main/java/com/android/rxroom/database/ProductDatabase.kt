package com.android.rxroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.rxroom.dao.ProductDao
import com.android.rxroom.model.ProductEntity


@Database(entities = [
    ProductEntity::class
], version = DbConstants.PRODUCT_DB_VERSION, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        private lateinit var instance: ProductDatabase

        fun getInstance(): ProductDatabase {
            return instance
        }

        internal fun initAppDatabase(context: Context) {
            instance = Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java, DbConstants.PRODUCT_DB)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

}

