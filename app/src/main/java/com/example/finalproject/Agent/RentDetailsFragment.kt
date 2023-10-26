package com.example.finalproject.Agent

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapters.MyDetailsFragmentAdapter
import com.example.finalproject.Profile
import com.example.finalproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList


class RentDetailsFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Profile>
    private lateinit var adapter: MyDetailsFragmentAdapter
    private lateinit var layoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        layoutManager = LinearLayoutManager(context)
        userRecyclerView = view.findViewById(R.id.list_view1)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = ArrayList()
        adapter = MyDetailsFragmentAdapter(requireContext(), userArrayList)
        userRecyclerView.adapter = adapter


        userArrayList = arrayListOf<Profile>()
        getUserData()

        return view
    }
    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Profiles")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var user = userSnapshot.getValue(Profile::class.java)
                        user?.let { userArrayList.add(it) }
                    }

                    adapter = MyDetailsFragmentAdapter(requireContext(),userArrayList)
                    userRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })




    }

}