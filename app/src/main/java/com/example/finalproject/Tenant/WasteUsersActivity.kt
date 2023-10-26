package com.example.finalproject.Tenant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.finalproject.R

class WasteUsersActivity : AppCompatActivity() {
    lateinit var back:CardView
    lateinit var header:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waste_users)
        header = findViewById(R.id.textViewWaste)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener {
            startActivity(Intent(applicationContext, WastePaymentActivity::class.java))
        }
        setValueToView()
    }

    private fun setValueToView() {
        header.text = intent.getStringExtra("group_name")
    }
}