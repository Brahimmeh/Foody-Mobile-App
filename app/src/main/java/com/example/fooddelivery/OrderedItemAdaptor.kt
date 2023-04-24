package com.example.fooddelivery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class OrderedItemAdaptor(private val OrderedList: List<OrderedItem>) :
    RecyclerView.Adapter<OrderedItemAdaptor.OrderedViewHolder>(){

    var onItemClick: ((OrderedItem) -> Unit)? = null


    class OrderedViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val OrderedTitle: TextView = itemView.findViewById(R.id.textView14)
        val OrderedQuantity: TextView = itemView.findViewById(R.id.textView12)
        val OrderedPrice: TextView = itemView.findViewById(R.id.textView17)
        val OrdredImage: ImageView = itemView.findViewById(R.id.imageView9)
        val pl: ImageView= itemView.findViewById(R.id.imageView10)
        val mi: ImageView= itemView.findViewById(R.id.imageView11)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):OrderedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_card_item, parent , false)
        return OrderedItemAdaptor.OrderedViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderedItemAdaptor.OrderedViewHolder, position: Int) {
        val ordered = OrderedList[position]
        holder.OrderedTitle.text=ordered.name
        holder.OrderedPrice.text=(ordered.price*ordered.Quantity).toString()+ " Dh"
        holder.OrderedQuantity.text="X"+ordered.Quantity.toString()
        holder.OrdredImage.setImageResource(ordered.img)

            holder.mi.setOnClickListener {
                ordered.Quantity--
                holder.OrderedQuantity.text="X"+ordered.Quantity.toString()
                holder.OrderedPrice.text=(ordered.price*ordered.Quantity).toString()+" Dh"
                val context= holder.OrderedPrice.context
                val intent = Intent(context, ShoppingCard::class.java)
                val ord: OrderedItem= OrderedItem(ordered.name,ordered.img,ordered.price,ordered.Quantity)
                intent.putExtra("price",ord)
                context.startActivity(intent)
            }

            holder.pl.setOnClickListener {
                ordered.Quantity++
                holder.OrderedQuantity.text="X"+ordered.Quantity.toString()
                holder.OrderedPrice.text=(ordered.price*ordered.Quantity).toString()+" Dh"
                val context= holder.OrderedPrice.context
                val intent = Intent(context, ShoppingCard::class.java)
                val ord: OrderedItem= OrderedItem(ordered.name,ordered.img,ordered.price,ordered.Quantity)
                intent.putExtra("price",ord)
                intent.putExtra("price",ord)
                context.startActivity(intent)
            }



    }

    override fun getItemCount(): Int {
        return OrderedList.size
    }

}

class GoToShoppingCard {

    val intent = Intent()
}