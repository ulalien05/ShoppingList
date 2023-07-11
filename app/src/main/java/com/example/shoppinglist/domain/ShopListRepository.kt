package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getShoppingList(): LiveData<List<ShopItem>>

    fun getShopItem(id: Int) : ShopItem

    fun editShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

}