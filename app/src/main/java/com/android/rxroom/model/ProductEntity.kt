package com.android.rxroom.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.android.rxroom.database.TableConstant
import com.google.gson.annotations.SerializedName

@Entity(tableName = TableConstant.PRODUCT)
data class ProductEntity(

	@field:SerializedName("small_description")
	var smallDescription: String? = null,

	@field:SerializedName("product_imageUrl")
	var productImageUrl: String? = null,

	@field:SerializedName("price")
	var price: Double? = null,

	@PrimaryKey
	@field:SerializedName("product_id")
	var productId: Int? = -1,

	@field:SerializedName("product_name")
	var productName: String? = null,

	@field:SerializedName("is_veg")
	var isVeg: Int? = null,

	var bookmarkStatus : Int? = 0



) {

	@Ignore
	override fun toString(): String {
		return "ProductEntity(smallDescription=$smallDescription, productImageUrl=$productImageUrl, price=$price, productId=$productId, productName=$productName, isVeg=$isVeg, bookmark=$bookmarkStatus)"
	}
}