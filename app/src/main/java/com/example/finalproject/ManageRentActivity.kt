package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView

class ManageRentActivity : AppCompatActivity() {

    lateinit var myListView:RecyclerView
    lateinit var myArrayList:ArrayList<Rent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_rent)

        myListView = findViewById(R.id.list_view1)
    }
}