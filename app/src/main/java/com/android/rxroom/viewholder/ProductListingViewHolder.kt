package com.android.rxroom.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.rxroom.R
import com.android.rxroom.listener.ProductBookmarListener
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.util.Constant
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_product_listing.view.*

/**
 * Created by ravisahani on 09,September,2019
RxRoom

 */
class ProductListingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindProduct(
        productEntity: ProductEntity?,
        bookmarkListener: ProductBookmarListener?
    ) {
        view.tv_product_name.text = productEntity?.productName ?: ""
        view.tv_product_desc.text = productEntity?.smallDescription ?: ""
        view.tv_product_price.text = "Rs ".plus(productEntity?.price.toString())

        Glide.with(view.context).load(productEntity?.productImageUrl).into(view.iv_product_image)


        if(productEntity?.bookmarkStatus == Constant.BOOKMARK_ADDED_STATUS){
            Glide.with(view.context).load(R.drawable.ic_bookmark_selected).into(view.iv_product_bookmark)
        }else{
            Glide.with(view.context).load(R.drawable.ic_bookmark_un_selected).into(view.iv_product_bookmark)
        }

        view.iv_product_bookmark.setOnClickListener { bookmarkListener?.addRemoveBookmark(productEntity!!) }

    }

}