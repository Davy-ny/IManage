package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class SecurityTabsActivity : AppCompatActivity() {
    lateinit var back:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_tabs)

        //Exiting page
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManageSecurityActivity::class.java))
        }

        var viewPager : ViewPager = findViewById(R.id.viewPager) as ViewPager
        var tabLayout = findViewById(R.id.tabLayout) as TabLayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(SecurityUsersFragments(), "Users")
        fragmentAdapter.addFragment(SecurityDetailsFragment(), "Details")
        fragmentAdapter.addFragment(SecurityPaymentsFragment(), "Payments")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}