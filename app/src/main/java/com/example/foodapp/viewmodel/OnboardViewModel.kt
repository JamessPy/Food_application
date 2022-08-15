package com.example.foodapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.toFragment
import com.example.foodapp.fragments.OnboardFragmentDirections

class OnboardViewModel : ViewModel(){
    fun toSignUp(view: View){
        Navigation.toFragment(view, OnboardFragmentDirections.actionOnboardFragmentToSignUpFragment())
    }
    fun toSignInp(view: View){
        Navigation.toFragment(view, OnboardFragmentDirections.actionOnboardFragmentToSignInFragment())
    }
}