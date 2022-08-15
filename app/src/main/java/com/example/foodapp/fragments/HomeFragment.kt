package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.IconAdapter
import com.example.foodapp.viewmodel.HomeViewModel
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentAnaSayfaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentAnaSayfaBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ana_sayfa, container, false)

        observeLiveData()
        binding.rvFoodHome.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        //binding.rvHorizontal.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
       // binding.rvHorizontal.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
/*
        val iconListesi = ArrayList<Icons>()


        val i1 = Icons(1,"icon1","Hamburger")
        val i2 = Icons(2,"icon2","Salatalar")
        val i3 = Icons(3,"icon3","Döner")
        val i4 = Icons(4,"icon4","Pizza")
        val i5 = Icons(5,"icon5","Atıştırmalık")
        val i6 = Icons(6,"icon6","Hamburger")
        val i7 = Icons(7,"icon7","Salatalar")
        val i8 = Icons(8,"icon8","Döner")
        val i9 = Icons(9,"icon9","Pizza")
        val i10 = Icons(10,"icon1","Atıştırmalık")

        iconListesi.add(i1)
        iconListesi.add(i2)
        iconListesi.add(i3)
        iconListesi.add(i4)
        iconListesi.add(i5)
        iconListesi.add(i6)
        iconListesi.add(i7)
        iconListesi.add(i8)
        iconListesi.add(i9)
        iconListesi.add(i10)

        val adapter2 = IconsAdapter(requireContext(),iconListesi)*/
        //binding.rvHorizontal.adapter = adapter2

        return binding.root
    }

    private fun observeLiveData(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(it)
            binding.foodAdapterVertical = adapter

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel

    }

}