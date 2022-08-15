package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodapp.R
import com.example.foodapp.toFragment
import com.example.foodapp.data.Food
import com.example.foodapp.databinding.FoodRowLayoutBinding
import com.example.foodapp.fragments.HomeFragmentDirections
import com.example.foodapp.fragments.SearchFragmentDirections

class FoodAdapter(var foodList: List<Food>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(var binding: FoodRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food){
            binding.food = food
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view : FoodRowLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.food_row_layout,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = with(holder) {
        val food = foodList[position]
        bind(food)
        binding.cardviewFoodHome.setOnClickListener {
            try {
                Navigation.toFragment(it, HomeFragmentDirections.actionAnasayfaFragmentToDetailFragment(food = food))

            }catch (IOException: IllegalArgumentException){
                Navigation.toFragment(it, SearchFragmentDirections.actionSearchFragmentToDetailFragment(food = food))
            }
        }

    }
    override fun getItemCount(): Int = foodList.size
}
@BindingAdapter("app:load_image")
fun loadImage(imageView: ImageView, foodImageName: String){
    val imageUrl ="http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
    imageView.load(imageUrl){}
}