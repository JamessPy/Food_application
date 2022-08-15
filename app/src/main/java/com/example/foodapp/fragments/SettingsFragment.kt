package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.toFragment
import com.example.foodapp.databinding.FragmentProfileBinding
import com.example.foodapp.databinding.FragmentSearchBinding
import com.example.foodapp.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        binding.email.text = auth.currentUser!!.email.toString()


        binding.Accept.setOnClickListener {
            auth.currentUser!!.updatePassword(binding.etSignUpPassword.text.toString())

            Navigation.toFragment(it,SettingsFragmentDirections.actionSettingsFragmentToSignInFragment())
        }
        binding.cancel.setOnClickListener {
            Navigation.toFragment(it,SettingsFragmentDirections.actionSettingsFragmentToAnasayfaFragment())
        }


        return binding.root
    }

}