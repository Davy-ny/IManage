package com.example.finalproject.Agent

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalproject.R
import com.google.firebase.auth.FirebaseAuth

class SecurityAdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var edtemail: EditText
        lateinit var edtpass: EditText
        lateinit var btnlogin: Button
        lateinit var txtforgot: TextView
        lateinit var progress: ProgressDialog
        lateinit var mAuth: FirebaseAuth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_admin_login)
        edtemail = findViewById(R.id.medtemail)
        edtpass = findViewById(R.id.edtpassw)
        btnlogin = findViewById(R.id.btnlogin)
        txtforgot = findViewById(R.id.txtforgot)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        btnlogin.setOnClickListener {
            var email = edtemail.text.toString().trim()
            var password = edtpass.text.toString().trim()
            //Check if user is submitting empty forms
            if (email.isEmpty()) {
                edtemail.setError("Please fill this input")
                edtemail.requestFocus()
            } else if (password.isEmpty()) {
                edtpass.setError("Please fill this input")
                edtpass.requestFocus()
            } else {
                //Proceed to login user
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        progress.dismiss()
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            startActivity(Intent(this, ManageSecurityActivity::class.java))
                            finish()
                        } else {
                            displayMessage("Invalid info", it.exception!!.message.toString())
                        }
                    }
            }
            txtforgot.setOnClickListener {

            }
        }
    }
    fun displayMessage(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", null)
        alertDialog.create().show()
    }
}