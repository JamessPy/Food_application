package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Food
import com.example.foodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    var foodRepository: FoodRepository):ViewModel() {
    var foodsList = MutableLiveData<List<Food>>()

    init {
        foodsList = foodRepository.getFoods()
    }

    fun searchFood(searchWord: String) {
        foodRepository.searchFood(searchWord)
    }

}