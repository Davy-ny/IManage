package com.example.finalproject.Tenant

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapters.CustomAdapter
import com.example.finalproject.R
import com.example.finalproject.Rentgroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WastePaymentActivity : AppCompatActivity() {
    lateinit var back:CardView
    private lateinit var dbRef: DatabaseReference
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var userArrayList:ArrayList<Rentgroup>
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waste_payment)

        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext, MakePaymentActivity::class.java))
        }

        //Initialisation
        userArrayList = ArrayList()
        adapter = CustomAdapter(this@WastePaymentActivity
            , userArrayList)
        myRecyclerView = findViewById(R.id.list_view2)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<Rentgroup>()
        getUserData()
    }
    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("WasteGroups")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var rent = userSnapshot.getValue(Rentgroup::class.java)
                        userArrayList.add(rent!!)
                    }
                    var adapter = CustomAdapter(this@WastePaymentActivity,userArrayList)
                    myRecyclerView.adapter = adapter
                    adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            Toast.makeText(applicationContext, "You clicked on item no. $position ", Toast.LENGTH_SHORT).show()
                            //startActivity(Intent(applicationContext,WasteUsersActivity::class.java))
                            val intent = Intent(this@WastePaymentActivity, WasteUsersActivity::class.java)

                            //put Extra data

                            val clickedWasteGroup = userArrayList[position]
                            intent.putExtra("group_name", clickedWasteGroup.groupName)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}