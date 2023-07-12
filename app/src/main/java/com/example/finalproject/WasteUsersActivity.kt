package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class WasteUsersActivity : AppCompatActivity() {
    lateinit var back:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waste_users)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener {
            startActivity(Intent(applicationContext, WastePaymentActivity::class.java))
        }
    }
}