package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.adapter.BasketAdapter
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentBasketBinding
import com.example.foodapp.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    private lateinit var userName: String
    var subTotal = 0
    var total = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_basket, container, false)
        userName = viewModel.username
        viewModel.basketFoodList.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.basketAdapter = BasketAdapter(arrayListOf(),viewModel)
            }else{
                binding.basketAdapter = BasketAdapter(it,viewModel)
            }
        }
        viewModel.viewModelSubTotal.observe(viewLifecycleOwner){
            binding.tvBasketFoodTotal.text = "₺$it"
        }

        binding.btnBasketConfirm.setOnClickListener {
            binding.rvBasketFragment.visibility = View.GONE
            val list = viewModel.basketFoodList.value
            if (list != null) {
                for (basket in list){
                    viewModel.deleteFood(basket.basket_food_id,userName)
                }
            }
            binding.tvBasketFoodTotal.text = "₺0"
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

}