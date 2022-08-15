package com.example.foodapp.data.response

import com.example.foodapp.data.Basket
import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("sepet_yemekler")
    val basketFoods: List<Basket>
)