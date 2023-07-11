package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShoppingListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShoppingList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShoppingList()
    }
}