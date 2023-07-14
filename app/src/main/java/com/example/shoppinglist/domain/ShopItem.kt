package com.example.shoppinglist.domain

data class ShopItem (
    var name: String,
    var quantity: Int,
    var active: Boolean,
    var id: Int = UNDEFINED_ID
){

companion object {
    const val UNDEFINED_ID = -1
}
}
