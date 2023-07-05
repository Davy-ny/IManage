package com.example.finalproject

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ActivityCreateRentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ManageRentActivity : AppCompatActivity() {
    lateinit var back: CardView
   private lateinit var dbRef:DatabaseReference
   private lateinit var myRecyclerView:RecyclerView
   private lateinit var userArrayList:ArrayList<Rentgroup>
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    lateinit var adapter:CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_rent)
    //Exiting page
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManagePropertyActivity::class.java))
        }

    //Initialisation
        userArrayList = ArrayList()
        adapter = CustomAdapter(this@ManageRentActivity, userArrayList)
        myRecyclerView = findViewById(R.id.list_view2)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<Rentgroup>()
        getUserData()

    }

    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Groups")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var rent = userSnapshot.getValue(Rentgroup::class.java)
                        userArrayList.add(rent!!)
                    }
                    myRecyclerView.adapter = CustomAdapter(this@ManageRentActivity,userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}