package com.example.finalproject

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var edtemail: EditText
    lateinit var edtname:EditText
    lateinit var edtpass: EditText
    lateinit var btnreg: Button
    lateinit var txtlogin: TextView
    lateinit var progress: ProgressDialog
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtname = findViewById(R.id.edt_name)
        edtemail = findViewById(R.id.edtemail)
        edtpass = findViewById(R.id.edtpass)
        btnreg = findViewById(R.id.btnreg)
        txtlogin = findViewById(R.id.txtlogin)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        btnreg.setOnClickListener{
            //Start by receiving data from user
            var email = edtemail.text.toString().trim()
            var password = edtpass.text.toString().trim()
            var name = edtname.text.toString().trim()
            //Check if user is submitting empty forms
            if(email.isEmpty()){
                edtemail.setError("Please fill this input")
                edtemail.requestFocus()
            }else if(name.isEmpty()){
                edtname.setError("Please Fill this input")
                edtname.requestFocus()
            }else if(password.isEmpty()){
                edtpass.setError("Please fill this input")
                edtpass.requestFocus()
            }else if(password.length < 6){
                edtpass.setError("Password is too short")
                edtpass.requestFocus()
            } else{
                //Proceed to register user
                progress.show()
                mAuth.createUserWithEmailAndPassword(email, password,)
                    .addOnCompleteListener{
                        progress.dismiss()
                        if (it.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }else{
                            displayMessage("Error", it.exception!!.message.toString())
                        }
                    }
            }
        }


        txtlogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }



    }
    fun displayMessage(title:String, message:String){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", null)
        alertDialog.create().show()

    }
    }
