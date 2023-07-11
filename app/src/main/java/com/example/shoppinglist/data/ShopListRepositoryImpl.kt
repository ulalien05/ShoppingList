package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private val shopItemList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var idIncrementer = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Item #$i", i, true)
            addShopItem(item)
        }
    }

    override fun getShoppingList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopItemList.find { it.id == id } ?: throw RuntimeException("Element $id is not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        shopItemList.remove(oldShopItem)
        addShopItem(shopItem)
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = idIncrementer++
        }
        shopItemList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
        updateList()
    }

    private fun updateList() {
        shopListLiveData.value = shopItemList.toList()
    }

}