package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MakePaymentActivity : AppCompatActivity() {
    lateinit var rent:CardView
    lateinit var waste:CardView
    lateinit var security:CardView
    lateinit var back:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_payment)

        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,HomeActivity::class.java))
        }

        rent = findViewById(R.id.rent_payment)
        rent.setOnClickListener{
            startActivity(Intent(applicationContext,RentPaymentActivity::class.java))
        }
        waste = findViewById(R.id.waste_payment)
        waste.setOnClickListener{
            startActivity(Intent(applicationContext,WastePaymentActivity::class.java))
        }

        security = findViewById(R.id.security_payment)
        security.setOnClickListener{
            startActivity(Intent(applicationContext,SecurityPaymentActivity::class.java))
        }
    }
}