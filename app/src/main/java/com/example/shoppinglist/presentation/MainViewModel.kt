package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShoppingListUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel(){

    private val repository = ShopListRepositoryImpl

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShoppingListUseCase.getShoppingList()

    fun changeStateOfShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(active = !shopItem.active)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}