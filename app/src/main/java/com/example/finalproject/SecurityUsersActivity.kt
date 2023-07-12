package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class SecurityUsersActivity : AppCompatActivity() {
    lateinit var back:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_users)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener {
            startActivity(Intent(applicationContext, SecurityPaymentActivity::class.java))
        }
    }
}