package com.example.fooddelivery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
 val video: VideoView = findViewById(R.id.VideoView1)
            video.setVideoURI(Uri.parse("android.resource://"+ packageName+"/"+R.raw.into_log))

//          val med: MediaController = MediaController(this);
                val bt1: Button = findViewById(R.id.google_sign)
                val bt2: Button = findViewById(R.id.signup)
                val bt3: Button = findViewById(R.id.signin)

        bt1.setOnClickListener {
            val intent = Intent(this, Sign_In_Google::class.java)
            startActivity(intent)
        }

        bt2.setOnClickListener {
            val intent = Intent(this, Sign_Up::class.java)
            startActivity(intent)
        }

        bt3.setOnClickListener {
            val intent = Intent(this, Sign_In_Email::class.java)
            startActivity(intent)
        }


            video.setMediaController(null)
            video.start()
        video.setOnClickListener{
            showImage()

        }
        video.setOnCompletionListener {

            showImage()
            video.start()
        }


    }

    private fun showImage() {
        //val img: ImageView = findViewById(R.id.cr1)
        val cn: ConstraintLayout = findViewById(R.id.Constraint2)
        cn.isVisible=true
       // img.setImageResource(R.drawable.black_circle)
    }
}

