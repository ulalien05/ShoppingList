package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputQuantity = MutableLiveData<Boolean>()
    val errorInputQuantity: LiveData<Boolean>
        get() = _errorInputQuantity

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _processDone = MutableLiveData<Unit>()
    val processDone: LiveData<Unit>
        get() = _processDone

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputQuantity: String?) {
        val name = parseName(inputName)
        val quantity = parseQuantity(inputQuantity)
        val fieldsValid = validateInput(name, quantity)
        if (fieldsValid) {
            val shopItem = ShopItem(name, quantity, true)
            addShopItemUseCase.addShopItem(shopItem)
            closeScreen()
        }
    }

    fun editShopItem(inputName: String?, inputQuantity: String?) {
        val name = parseName(inputName)
        val quantity = parseQuantity(inputQuantity)
        val fieldsValid = validateInput(name, quantity)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, quantity = quantity)
                editShopItemUseCase.editShopItem(item)
                closeScreen()
            }
        }
    }

    private fun parseName(inputName: String?) : String {
        return inputName?.trim() ?: ""
    }

    private fun parseQuantity(inputQuantity: String?) : Int {
        return try {
            inputQuantity?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, quantity: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (quantity <= 0) {
            _errorInputQuantity.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }

    public fun resetErrorInoutQuantity() {
        _errorInputQuantity.value = false
    }

    private fun closeScreen() {
        _processDone.value = Unit
    }




}