package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.foodapp.R
import com.example.foodapp.data.Food
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var navArgs: DetailFragmentArgs
    private lateinit var viewModel: DetailViewModel

    var quantity = 0
    var subTotal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)

        val bundle : DetailFragmentArgs by navArgs()
        navArgs = bundle
        binding.food = navArgs.food
        binding.viewModel = viewModel
        binding.detailFragment = this

        viewModel.basketFoodList.observe(viewLifecycleOwner){
            for (basket in it){
                if(basket.basket_food_name == navArgs.food.foodName){
                    binding.tvDetailQuantity.text = basket.basket_order_quantity.toString()
                    subTotal = basket.basket_food_price * basket.basket_order_quantity
                    binding.tvDetailSubTotal.text = "₺$subTotal"
                }
            }

        }
        return binding.root
    }
    fun AddToCart(food: Food, quantity: Int){
        viewModel.AddToCart(food,quantity)
        Snackbar.make(requireView(),"Ürün sepete eklendi",Toast.LENGTH_SHORT).show()
    }
    fun Add(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        quantity++
        subTotal = quantity * navArgs.food.foodPrice
        binding.tvDetailSubTotal.text = "₺$subTotal"
        binding.tvDetailQuantity.text = quantity.toString()

    }
    fun Delete(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        if(quantity!=0){
            quantity--
            subTotal = quantity * navArgs.food.foodPrice
            binding.tvDetailSubTotal.text = "₺$subTotal"
            binding.tvDetailQuantity.text = quantity.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}