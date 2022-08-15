package com.example.foodapp.register

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.toFragment
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {
    fun signIn(auth: FirebaseAuth,view:View, email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Navigation.toFragment(view, SignInFragmentDirections.actionSignInFragmentToAnasayfaFragment())
            }
            .addOnFailureListener {
                Toast.makeText(view.context, "Email yada şifre yanlış",Toast.LENGTH_SHORT).show()
            }
    }
    fun toSignUp(view: View){
        Navigation.toFragment(view,SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }
}