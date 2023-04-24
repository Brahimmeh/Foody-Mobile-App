package com.example.fooddelivery


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class ShoppingCard : AppCompatActivity() {

    private lateinit var recyclerView3: RecyclerView
    private lateinit var orderedAdaptor: OrderedItemAdaptor
    private lateinit var orderedList:ArrayList<OrderedItem>




    object AddItemOrd{
        var ordeL:ArrayList<OrderedItem> =ArrayList()
        var total: Int=0
        fun addil()
        { }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_card)

        val del:TextView= findViewById(R.id.textView13)
        val mov:TextView= findViewById(R.id.textView21)
        val valid:TextView= findViewById(R.id.textView11)


        startShopping()

        valid.setOnClickListener {
            AddItemOrd.ordeL.clear()
            val intent = Intent(this, Ready::class.java)
            startActivity(intent)
        }

        del.setOnClickListener {
            AddItemOrd.ordeL.clear()
            val intent = Intent(this, ShoppingCard::class.java)
            startActivity(intent)
        }

        mov.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun startShopping() {
            recyclerView3=findViewById(R.id.res3)
            recyclerView3.setHasFixedSize(true)
            recyclerView3.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL , false)
            orderedList=ArrayList()


            init()
            addDatatoOrdered()

            orderedAdaptor= OrderedItemAdaptor(orderedList)
            recyclerView3.adapter = orderedAdaptor
        }

        private fun init() {
       val ordered= intent.getParcelableExtra<OrderedItem>("List")
        if (ordered != null) {
            AddItemOrd.ordeL.add(OrderedItem(ordered.name,ordered.img,ordered.price,ordered.Quantity))
        }
        }
    private fun addDatatoOrdered() {
        var num:Int=0
        AddItemOrd.ordeL.forEach {
            orderedList.add(OrderedItem(it.name,it.img,it.price,it.Quantity))
            num+=it.price*it.Quantity
        }
        val TotalOrd: TextView= findViewById(R.id.textView18)
        val Tot: TextView= findViewById(R.id.textView20)
        val prc= intent.getParcelableExtra<OrderedItem>("price")

        TotalOrd.text= num.toString()+" Dh"
        num += 25
        Tot.text=num.toString()+ " Dh"

        if(prc != null)
        {
            AddItemOrd.ordeL.find { it.name == prc.name }?.Quantity = prc.Quantity
            AddItemOrd.ordeL.find { it.name == prc.name }?.price= prc.price
            num=(prc.price*prc.Quantity)
            TotalOrd.text= num.toString()
            num += 25
            Tot.text=num.toString()
            if(prc.Quantity==0)
            {
                AddItemOrd.ordeL.remove(prc)
            }
            val intent = Intent(this, ShoppingCard::class.java)
            startActivity(intent)
    }
    }


    }
