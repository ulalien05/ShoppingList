package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopItem.Companion.UNDEFINED_ID
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopItemList = mutableListOf<ShopItem>()

    private var idIncrementer = 0

    override fun getShoppingList(): List<ShopItem> {
        return shopItemList
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopItemList.find { it.id == id } ?: throw RuntimeException("Element $id is not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        shopItemList.remove(oldShopItem)
        shopItemList.add(shopItem)
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == UNDEFINED_ID) {
            shopItem.id = idIncrementer++
        }
        shopItemList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
    }
}