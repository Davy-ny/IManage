package com.example.finalproject.Agent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.finalproject.Adapters.FragmentAdapter
import com.example.finalproject.R
import com.google.android.material.tabs.TabLayout

class RentTabsActivity : AppCompatActivity() {
    lateinit var back: CardView
    lateinit var header:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent_tabs)

        header = findViewById(R.id.group_name)

        //Exiting page
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext, ManageRentActivity::class.java))
        }

        var viewPager :ViewPager = findViewById(R.id.viewPager) as ViewPager
        var tabLayout = findViewById(R.id.tabLayout) as TabLayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(RentUsersFragment(), "Users")
        fragmentAdapter.addFragment(RentDetailsFragment(), "Details")
        fragmentAdapter.addFragment(RentPaymentsFragment(), "Payments")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        setValueToView()
    }

    private fun setValueToView() {
        header.text = intent.getStringExtra("rent_name")
    }
}