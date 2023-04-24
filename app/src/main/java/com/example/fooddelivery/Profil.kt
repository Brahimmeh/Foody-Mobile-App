package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class Profil : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        auth= FirebaseAuth.getInstance()
        val current= auth.currentUser

        val email = current?.email
        var Familyname = current?.displayName
        val img= current?.photoUrl

        val profpic: ImageView = findViewById(R.id.imageView22)

        if(Familyname.isNullOrBlank())
        {
            val ref = FirebaseDatabase.getInstance().getReference("user")
            val uid= auth.currentUser?.uid

            ref.child(uid.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    val nam = it.child("name").value
                    Familyname= nam.toString()
                    findViewById<TextView>(R.id.textView23).text= Familyname
                }
            }
        }
        else{
            findViewById<TextView>(R.id.textView23).text= Familyname
        }

        if(img == null)
        {
            val ref = FirebaseDatabase.getInstance().getReference("user")
            val uid= auth.currentUser?.uid

            ref.child(uid.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    val im = it.child("image").value.toString()
                    Picasso.with(this).load(im).rotate(90F).into(profpic)
                }}
        }
        else
        {
            Picasso.with(this).load(img).into(profpic)
        }


        findViewById<TextView>(R.id.textView26).text= email
        Picasso.with(this).load(img).into(profpic)

        findViewById<Button>(R.id.button2).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Intro::class.java))
        }


    }
}