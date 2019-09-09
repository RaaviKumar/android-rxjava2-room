package com.android.rxroom.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rxroom.R
import com.android.rxroom.adapter.ProductListingAdapter
import com.android.rxroom.listener.ProductBookmarListener
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.viewmodel.BookmarkViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : BaseActivity(), ProductBookmarListener {


    var bookmarkViewModel: BookmarkViewModel? = null
    var bookmarkListingAdapter: ProductListingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        initViewModel()
        setUpRecyclerView()
        getProducts()

    }

    private fun initViewModel() {
        bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
    }

    private fun getProducts() {
       getProductList()
    }

    private fun getProductList() {
        addSubscription(bookmarkViewModel?.getBookmarkedProducts()?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { products ->
                setAdapter(products)
            })
    }

    private fun setAdapter(productListData: List<ProductEntity>) {

        if (bookmarkListingAdapter == null) {
            bookmarkListingAdapter = ProductListingAdapter()
            bookmarkListingAdapter?.setProductListData(productListData)
            bookmarkListingAdapter?.setProductBookmarListener(this)
            rv_bookmark_list.adapter = bookmarkListingAdapter
        } else {
            bookmarkListingAdapter?.setProductListData(productListData)
            bookmarkListingAdapter?.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerView(){
        rv_bookmark_list.layoutManager = LinearLayoutManager(this)
    }

    override fun addRemoveBookmark(product: ProductEntity) {
        bookmarkViewModel?.addRemoveBookmark(product)
    }


}
