package com.example.foodapp.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.toFragment
import com.example.foodapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temViewModel : SignInViewModel by viewModels()
        viewModel = temViewModel
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.signInFragment = this
        binding.viewModel = viewModel
        passwordFocusListener()
        return binding.root
    }
    fun signIn(view: View,email:String,password:String){
        try {
            viewModel.signIn(auth,view,email,password)

        }catch (IOException: IllegalArgumentException){
            Toast.makeText(requireContext(), "Email ve şifre gerekli",Toast.LENGTH_SHORT).show()
        }
    }
    fun signUp(view: View){
        viewModel.toSignUp(view)
    }
    private fun passwordFocusListener(){
        binding.etSignInPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                binding.signInTextFieldPassword.helperText = validPassword()
            }
        })
    }

    private fun validPassword(): String? {
        val password = binding.etSignInPassword.text.toString()
        if(password.isEmpty()){
            return "Şifre Gerekli"
        }
        return null
    }
}