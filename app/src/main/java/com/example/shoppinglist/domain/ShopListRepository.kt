package com.example.shoppinglist.domain

interface ShopListRepository {

    fun getShoppingList(): List<ShopItem>

    fun getShopItem(id: Int) : ShopItem

    fun editShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

}