package com.example.foodapp

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.toFragment(view: View, navDirections: NavDirections){
    findNavController(view).navigate(navDirections)
}

