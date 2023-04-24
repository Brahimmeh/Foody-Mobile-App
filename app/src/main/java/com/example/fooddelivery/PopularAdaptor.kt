package com.example.fooddelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PopularAdaptor(private val PopularList: List<Popular>) :
    RecyclerView.Adapter<PopularAdaptor.PopularViewHolder>() {

    var onItemClick: ((Popular) -> Unit)? = null

        class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PopularImageView: ImageView = itemView.findViewById(R.id.imageView8)
        val PopularNameView: TextView = itemView.findViewById(R.id.nameRes2)
        val PopularPriceView: TextView = itemView.findViewById(R.id.priceRes2)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_items, parent , false)
        return PopularAdaptor.PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popular = PopularList[position]
        holder.PopularImageView.setImageResource(popular.img)
        holder.PopularNameView.text= popular.name
        holder.PopularPriceView.text=popular.price + " Dh"


        holder.itemView.setOnClickListener {
            onItemClick?.invoke(popular)
        }
    }

    override fun getItemCount(): Int {
        return PopularList.size
    }

}