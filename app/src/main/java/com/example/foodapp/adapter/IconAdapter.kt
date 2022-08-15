package com.example.foodapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.Icons
import com.example.foodapp.databinding.FoodHorizontalBinding

class IconAdapter(var mContext: Context, var iconListesi:List<Icons>) :
    RecyclerView.Adapter<IconAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: FoodHorizontalBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: FoodHorizontalBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = FoodHorizontalBinding.inflate(layoutInflater, parent, false)

        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val icon = iconListesi.get(position)
        val t = holder.tasarim

        t.imageViewIcon.setImageResource(
            mContext.resources.getIdentifier(
                icon.imageName,
                "drawable",
                mContext.packageName
            )
        )
        t.imageAciklama.text = icon.aciklama

    }

    override fun getItemCount(): Int {
        return iconListesi.size
    }
}