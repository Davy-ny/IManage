package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class WasteTabsActivity : AppCompatActivity() {
    lateinit var back:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waste_tabs)

        //Exiting page
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManageWasteActivity::class.java))
        }

        var viewPager : ViewPager = findViewById(R.id.viewPager) as ViewPager
        var tabLayout = findViewById(R.id.tabLayout) as TabLayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(WasteUsersFragment(), "Users")
        fragmentAdapter.addFragment(WasteDetailsFragment(), "Details")
        fragmentAdapter.addFragment(WastePaymentsFragment(), "Payments")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}