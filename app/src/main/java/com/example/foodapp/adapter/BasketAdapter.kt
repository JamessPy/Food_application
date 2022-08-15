package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.Basket
import com.example.foodapp.databinding.RowBasketLayoutBinding
import com.example.foodapp.viewmodel.BasketViewModel
import com.google.android.material.snackbar.Snackbar


class BasketAdapter(
    var basketList: List<Basket>,
    var viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    var total = 0

    class BasketViewHolder(var binding: RowBasketLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(basket: Basket) {
            binding.basket = basket
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view: RowBasketLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_basket_layout, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) = with(holder) {
        val basket = basketList[position]
        //val mContext = binding.root.context
        bind(basket)
        var subTotal = basket.basket_food_price * basket.basket_order_quantity
        total += subTotal
        binding.ivFoodDelete.setOnClickListener {
            Snackbar.make(binding.root, "Silmek istediginize emin misiniz", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.deleteFood(basket.basket_food_id, viewModel.username)
                    total -= (basket.basket_food_price * basket.basket_order_quantity)
                }.show()
        }

        binding.subTotalResult = "₺ $subTotal"
        viewModel.viewModelSubTotal.value = total
    }

    override fun getItemCount(): Int = basketList.size
}