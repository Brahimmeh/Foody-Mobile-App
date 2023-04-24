package com.example.fooddelivery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Intro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro)

        val startButton: Button = findViewById(R.id.strbtn)
        startButton.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }
}