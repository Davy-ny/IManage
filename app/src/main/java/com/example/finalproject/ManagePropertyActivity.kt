package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class ManagePropertyActivity : AppCompatActivity() {
    lateinit var create_rent:CardView
    lateinit var create_security:CardView
    lateinit var create_waste:CardView
    lateinit var back:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_property)
        create_rent = findViewById(R.id.create_rent)
        create_rent.setOnClickListener{
            startActivity(Intent(applicationContext,CreateRentActivity::class.java))
        }

        create_security = findViewById(R.id.create_security)
        create_security.setOnClickListener{
            startActivity(Intent(applicationContext,CreateRentActivity::class.java))
        }

        create_waste = findViewById(R.id.create_waste)
        create_waste.setOnClickListener{
            startActivity(Intent(applicationContext,CreateRentActivity::class.java))
        }

        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,HomeActivity::class.java))
        }
    }
}