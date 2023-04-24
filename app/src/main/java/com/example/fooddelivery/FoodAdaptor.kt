package com.example.fooddelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class FoodAdaptor(private val foodList: List<Foods>) :
    RecyclerView.Adapter<FoodAdaptor.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImageView: ImageView = itemView.findViewById(R.id.imageView6)
        val foodNameView: TextView = itemView.findViewById(R.id.textRes1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_res1, parent , false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodImageView.setImageResource(food.img)
        holder.foodNameView.text= food.name

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}