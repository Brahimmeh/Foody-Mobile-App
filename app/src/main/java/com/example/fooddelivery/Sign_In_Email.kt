package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fooddelivery.databinding.ActivitySignInEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Sign_In_Email : AppCompatActivity() {

    private lateinit var binding: ActivitySignInEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()


        binding.textView.setOnClickListener {
            val intent = Intent(this,Sign_Up::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty())
            {
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful)
                        {
                            Toast.makeText(this,"Sign In Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }

                    else{
                            Toast.makeText(this,"Wrong Password or Email", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
            else
            {
                Toast.makeText(this,"Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}