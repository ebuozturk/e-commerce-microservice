package com.ebuozturk.productcategory.dto.product;

import com.ebuozturk.productcategory.dto.category.CategoryDto

data class ProductDto @JvmOverloads constructor(
    val id: String? = "",
    val name: String,
    val description: String,
    val unitPrice: Double,
    val quantityPerUnit: Int,
    val unitsInStock: Int ,
    val category: CategoryDto
){

}

