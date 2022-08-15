package com.example.foodapp.data.response

import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("success") var success: Int,
    @SerializedName("message") var message: String
)