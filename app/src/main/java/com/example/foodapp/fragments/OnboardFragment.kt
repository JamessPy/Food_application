package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentOnboardBinding
import com.example.foodapp.viewmodel.OnboardViewModel

class OnboardFragment : Fragment() {
    private lateinit var binding:FragmentOnboardBinding
    private val viewModel: OnboardViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_onboard, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}

