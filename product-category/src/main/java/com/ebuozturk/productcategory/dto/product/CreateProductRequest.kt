package com.ebuozturk.productcategory.dto.product;


data class CreateProductRequest @JvmOverloads constructor(
    val name:String,
    val description:String,
    val unitPrice:Double,
    val quantityPerUnit:Int,
    val unitsInStock:Int,
    val categoryId:String
) {
}
