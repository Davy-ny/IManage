package com.example.finalproject.Agent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.finalproject.Common.HomeActivity
import com.example.finalproject.R

class ManagePropertyActivity : AppCompatActivity() {
    lateinit var create_rent:CardView
    lateinit var create_security:CardView
    lateinit var create_waste:CardView
    lateinit var back:CardView
    lateinit var man_rent:TextView
    lateinit var man_security:TextView
    lateinit var man_waste:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_property)

        man_rent = findViewById(R.id.man_rent)
        man_rent.setOnClickListener{
            startActivity(Intent(applicationContext, RentAdminLogin::class.java))
        }

        man_security = findViewById(R.id.man_sec)
        man_security.setOnClickListener{
            startActivity(Intent(applicationContext, SecurityAdminLogin::class.java))
        }

        man_waste = findViewById(R.id.man_waste)
        man_waste.setOnClickListener{
            startActivity(Intent(applicationContext, WasteAdminLogin::class.java))
        }

        create_rent = findViewById(R.id.create_rent)
        create_rent.setOnClickListener{
            startActivity(Intent(applicationContext, CreateRentActivity::class.java))
        }

        create_security = findViewById(R.id.create_security)
        create_security.setOnClickListener{
            startActivity(Intent(applicationContext, CreateSecurityActivity::class.java))
        }

        create_waste = findViewById(R.id.create_waste)
        create_waste.setOnClickListener{
            startActivity(Intent(applicationContext, CreateWasteActivity::class.java))
        }

        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }
    }
}