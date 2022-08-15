package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Basket
import com.example.foodapp.data.Food
import com.example.foodapp.data.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var basketRepo: BasketRepository) : ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var userName = basketRepo.username
    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
    }

    fun loadAllFoodBasket() {
        basketRepo.getFoodQuantity()

    }
    fun AddToCart(food: Food, quantity: Int) {
        if(!basketFoodList.value.isNullOrEmpty()){
            for(basketFood in basketFoodList.value!!){
                if(basketFood.basket_food_name == food.foodName){
                    basketRepo.deleteFoodBasket(basketFood.basket_food_id,userName)
                }
            }
        }
        basketRepo.addFoodBasket(
            food.foodName,
            food.foodImageName,
            food.foodPrice,
            quantity,
            userName
        )
    }
}