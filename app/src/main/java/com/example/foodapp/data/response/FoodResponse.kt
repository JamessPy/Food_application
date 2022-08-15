package com.example.foodapp.data.response

import com.example.foodapp.data.Food
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("yemekler")
    val foods: List<Food>
)