package com.example.foodapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.utils.FoodDao
import com.example.foodapp.data.Food
import com.example.foodapp.data.response.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FoodRepository @Inject constructor(var foodDao: FoodDao) {
    var foodsList : MutableLiveData<List<Food>>
    var foodLoading: MutableLiveData<Boolean> = MutableLiveData()
    init {
        foodsList = MutableLiveData()
    }

    fun getFoods() : MutableLiveData<List<Food>> {
        return foodsList
    }
    fun getLoading() : MutableLiveData<Boolean> {
        return foodLoading
    }

    fun getAllFoods(){
        foodLoading.value = true
        foodDao.getAllFood().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>?, response: Response<FoodResponse>) {
                val list = response.body().foods
                foodsList.value = list
                foodLoading.value = false
                for (i in list){
                    Log.e("test",i.toString())
                }

            }

            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {
                Log.e("test","hata")

            }
        })
    }
    fun searchFood(searchWord:String){
        foodLoading.value = true
        foodDao.getAllFood().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>?, response: Response<FoodResponse>) {
                val list = response.body().foods
                foodsList.value = list
                val searchList = ArrayList<Food>()
                for(food in list){
                    if(food.foodName.lowercase().contains(searchWord.lowercase())){
                        searchList.add(food)
                    }
                }
                foodsList.value = searchList
                foodLoading.value = false
            }

            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}

        })
    }


}