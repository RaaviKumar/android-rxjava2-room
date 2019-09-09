package com.android.rxroom.listener

import com.android.rxroom.model.ProductEntity

/**
 * Created by ravisahani on 09,September,2019
RxRoom

 */
interface ProductBookmarListener {
    fun addRemoveBookmark(product : ProductEntity)
}