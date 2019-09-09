package com.android.rxroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rxroom.R
import com.android.rxroom.listener.ProductBookmarListener
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.viewholder.ProductListingViewHolder

/**
 * Created by ravisahani on 09,September,2019
RxRoom

 */
class ProductListingAdapter : RecyclerView.Adapter<ProductListingViewHolder>() {

    var productListingData : List<ProductEntity>? = null
    var bookmarListener: ProductBookmarListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_product_listing,parent,false)
        return ProductListingViewHolder(view)
    }

    override fun getItemCount(): Int {
       return productListingData?.size?:0
    }

    override fun onBindViewHolder(holder: ProductListingViewHolder, position: Int) {
        holder.bindProduct(productListingData?.get(position),bookmarListener)
    }

    fun setProductListData(productListingData : List<ProductEntity>){
        this.productListingData = productListingData
    }

    fun setProductBookmarListener(bookmarListener: ProductBookmarListener){
        this.bookmarListener = bookmarListener
    }

}