package com.example.shoppinglist.domain

class GetShoppingListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShoppingList(): List<ShopItem> {
        return shopListRepository.getShoppingList()
    }
}