package com.example.fooddelivery

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


class DetailsPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)
        val plusSi:ImageView= findViewById(R.id.plusim)
        val mino:ImageView = findViewById(R.id.minim)
        val titleElem: TextView = findViewById(R.id.titletxt)
        val imgElem: ImageView = findViewById(R.id.imgelem)
        val priceElem: TextView = findViewById(R.id.textPrice)
        val numb: TextView= findViewById(R.id.numorder)
        val des: TextView=findViewById(R.id.descriptionFood)
        var numo: Int = 1

        lateinit var name:String
        lateinit var price:String
        var img : Int=0

         val food= intent.getParcelableExtra<Popular>("food")
         val validate: TextView= findViewById(R.id.addtocart)
        if(food != null)
        {


            titleElem.text=food.name
            imgElem.setImageResource(food.img)
            priceElem.text=(food.price)
            numb.text=numo.toString()
            des.text=food.des

            name=food.name
            price=food.price
            img=food.img


            mino.setOnClickListener {
                numo--
                if(numo<0)
                    numo=0
                numb.text= numo.toString()
            }

            plusSi.setOnClickListener {
                numo++
                numb.text = numo.toString()
            }

        }

        validate.setOnClickListener {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this)
                // set message of alert dialog
                dialogBuilder.setMessage("Do you want to add this item to your Card?")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Add", DialogInterface.OnClickListener {
                            dialog, id -> finish()

                        val ord= OrderedItem(name,img,price.toInt(),numo)
                        val intent = Intent(this, ShoppingCard::class.java)
                        intent.putExtra("List",ord)
                        startActivity(intent)
                    })
                    // negative button text and action
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Confirmation Of The Order")
                // show alert dialog
                alert.show()
            }


        }





    }


