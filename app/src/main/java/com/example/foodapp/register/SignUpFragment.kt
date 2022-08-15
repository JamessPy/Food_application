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
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temViewModel : SignUpViewModel by viewModels()
        viewModel = temViewModel
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.signUpFragment = this
        binding.viewModel = viewModel
        confirmPasswordFocusListener()
        return binding.root
    }
    fun signUp(view: View,email:String,password:String){
        try {
            viewModel.signUp(auth,view,email,password)

        }catch (IOException: IllegalArgumentException){
            Toast.makeText(requireContext(), "Email veya şifrenizi kontrol ediniz",Toast.LENGTH_SHORT).show()
        }
    }
    private fun confirmPasswordFocusListener(){
        binding.etSignUpConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val password = binding.etSignUpPassword.text.toString()
                val confirmPassword = binding.etSignUpConfirmPassword.text.toString()
                if(password != confirmPassword){
                    binding.signUpTextFieldConfirmPassword.helperText = "Şifreler aynı değil"
                }else{
                    binding.signUpTextFieldConfirmPassword.helperText = null
                }
            }
        })
    }
}