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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        binding.buttonCikisYap.setOnClickListener {
            auth.signOut()
            Navigation.toFragment(it,ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
        }
        binding.btnSettings.setOnClickListener {
            Navigation.toFragment(it,ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
        }
        binding.tvUsername.text = auth.currentUser!!.email.toString()
        return binding.root
    }
}