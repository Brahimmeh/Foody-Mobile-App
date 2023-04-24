package com.example.fooddelivery

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fooddelivery.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.util.*

class Sign_Up : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var storage:FirebaseStorage
    private val REQUEST_IMAGE_GALLERY = 123
    private val REQUEST_IMAGE_CAMERA = 134
    private lateinit var img:Uri
    private lateinit var email :String
    private lateinit var pass :String
    private lateinit var passC :String
    private lateinit var name :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        storage= FirebaseStorage.getInstance()

        binding.profpic.setImageResource(R.drawable.admin_user_icon)


        binding.textView.setOnClickListener {
            val intent = Intent(this,Sign_In_Email::class.java)
            startActivity(intent)
        }

        binding.profpic.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Your Profile Picture")
            builder.setPositiveButton("Import") { dialogInterface, i ->
                dialogInterface.dismiss()
                val intent = Intent(Intent.ACTION_PICK)
                intent.type= "image/*"
                startActivityForResult(intent,REQUEST_IMAGE_GALLERY)

            }

            builder.setNegativeButton("Cancel") { dialogInterface, i ->
                dialogInterface.dismiss()
            }

            val dialog: AlertDialog= builder.create()
            dialog.show()
        }

        binding.button.setOnClickListener {
            email = binding.emailEt.text.toString()
            pass = binding.passET.text.toString()
            passC = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && passC.isNotEmpty() && img != null)
            {
                if(pass == passC)
                {

                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                          uploadData()
                        }

                        else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                else if(pass != passC)
                {
                    Toast.makeText(this,"Unmatching Password !!", Toast.LENGTH_SHORT).show()
                }
            }

            else
            {
                Toast.makeText(this,"Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadData() {
        val reference = storage.reference.child("profile").child(Date().time.toString())
        reference.putFile(img).addOnCompleteListener{
            if(it.isSuccessful)
            {
                reference.downloadUrl.addOnSuccessListener {  task ->
                    loadinfo(task.toString())
                }
            }

        }
    }

    private fun loadinfo(imageUrl: String) {
        email = binding.emailEt.text.toString()
        pass = binding.passET.text.toString()
        passC = binding.confirmPassEt.text.toString()
        name = binding.userEt.text.toString()
        val user = User(name,email,imageUrl)
        val uid= auth.currentUser?.uid
        FirebaseDatabase.getInstance().getReference("user")
            .child(uid.toString()).setValue(user).addOnSuccessListener{

                Toast.makeText(this,"User Created", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Sign_In_Email::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this,"User Non Created", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if(requestCode== REQUEST_IMAGE_GALLERY && resultCode==Activity.RESULT_OK && data!=null)
            {
                img= data.data!!
                binding.profpic.setImageURI(img)
            }

        else{
                Toast.makeText(this,"Photo Uplaod Failed", Toast.LENGTH_SHORT).show()
            }
    }
}