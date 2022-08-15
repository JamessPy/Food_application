package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Food
import com.example.foodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var foodRepo: FoodRepository
) : ViewModel() {

    var foodsList = MutableLiveData<List<Food>>()
    var foodLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadFoods()
        foodsList = foodRepo.getFoods()
        foodLoading = foodRepo.getLoading()
    }

    fun loadFoods() {
        foodRepo.getAllFoods()
    }

}