package com.example.finalproject.Common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.finalproject.FirebaseAuth.LoginActivity
import com.example.finalproject.R
import com.google.firebase.auth.FirebaseAuth

class ResetActivity : AppCompatActivity() {
    lateinit var back:CardView
    lateinit var emailEditText: EditText
    lateinit var resetButton: Button
    lateinit var mAuth: FirebaseAuth
    lateinit var username: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        emailEditText = findViewById(R.id.emailEditText)
        resetButton = findViewById(R.id.btn_reset)
        mAuth = FirebaseAuth.getInstance()

        // Initialize Firebase Auth
        resetButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot password")
            //val view = layoutInflater.inflate(R.layout.dialog_reset_password, null)

            val email = emailEditText.text.toString().trim()

            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                // Handle empty email error

            }
        }
    }
    private fun forgotpassword(username: EditText) {
        if (username.text.toString().isEmpty())
            return
    }

    private fun resetPassword(email: String) {
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "verification email sent 🥳🥳", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                // Password reset email sent successfully
                // Handle success behavior
                else {
                    displayMessage("ERROR", it.exception!!.message.toString())
                    // Failed to send password reset email
                    // Handle error behavior
                }
            }
    }


    fun displayMessage(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("ok", null)
        alertDialog.create().show()
    }
}