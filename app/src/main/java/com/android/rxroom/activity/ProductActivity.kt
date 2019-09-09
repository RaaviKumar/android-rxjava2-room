package com.android.rxroom.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rxroom.R
import com.android.rxroom.adapter.ProductListingAdapter
import com.android.rxroom.listener.ProductBookmarListener
import com.android.rxroom.model.ProductEntity
import com.android.rxroom.viewmodel.ProductViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : BaseActivity(), ProductBookmarListener {


    var productViewModel: ProductViewModel? = null
    var productListingAdapter: ProductListingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        initViewModel()
        setUpRecyclerView()
        getProducts()
        setMenuNavigationListener()
    }

    private fun initViewModel() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }

    private fun getProducts() {
        showProgressBar()
        hitProductApi()
        getProductList()
    }

    private fun hitProductApi() {
        productViewModel?.getProducts()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<ProductEntity>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    addSubscription(d)
                }

                override fun onNext(productData: List<ProductEntity>) {
                    hideProgressBar()
                }

                override fun onError(e: Throwable) {
                    hideProgressBar()
                }

            })
    }

    private fun getProductList() {
        addSubscription(productViewModel?.getProductList()?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { products ->
                setAdapter(products)
            })
    }

    private fun showProgressBar() {
        pb_product.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pb_product.visibility = View.GONE
    }

    private fun setAdapter(productListData: List<ProductEntity>) {

        if (productListingAdapter == null) {
            productListingAdapter = ProductListingAdapter()
            productListingAdapter?.setProductListData(productListData)
            productListingAdapter?.setProductBookmarListener(this)
            rv_product_list.adapter = productListingAdapter
        } else {
            productListingAdapter?.setProductListData(productListData)
            productListingAdapter?.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerView() {
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }

    override fun addRemoveBookmark(product: ProductEntity) {
        productViewModel?.addRemoveBookmark(product)
    }

    private fun setMenuNavigationListener() {
        bottom_menu_product.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_product -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_bookmark -> {
                    startActivity(Intent(this, BookmarkActivity::class.java))
                    return@OnNavigationItemSelectedListener false
                }

            }
            false
        })
    }


}
