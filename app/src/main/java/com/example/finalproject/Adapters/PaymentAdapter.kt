package com.example.finalproject.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.User
import com.google.firebase.database.core.Context

class PaymentAdapter(private val context: Context, private val userList :ArrayList<User>):RecyclerView.Adapter<PaymentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_6,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = userList[position]
        holder.mTxtHouseNumber.text = currentItem.houseNo
    }

    class MyViewHolder(itemView :View ) :RecyclerView.ViewHolder (itemView){
        var mTxtHouseNumber: TextView = itemView.findViewById(R.id.house_number)
    }



}