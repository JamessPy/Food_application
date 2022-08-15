package com.example.foodapp.register

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.toFragment
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel() {
    fun signUp(auth: FirebaseAuth, view: View, email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Navigation.toFragment(view,SignUpFragmentDirections.actionSignUpFragmentToAnasayfaFragment())
            }
            .addOnFailureListener {
                Toast.makeText(view.context, "Email yada şifre yanlış",Toast.LENGTH_SHORT).show()
            }
    }
    fun toSignInp(view: View){
        Navigation.toFragment(view, SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
    }
}