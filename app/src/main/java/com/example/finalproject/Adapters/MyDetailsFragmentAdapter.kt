package com.example.finalproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Profile
import com.example.finalproject.R

class MyDetailsFragmentAdapter(private var context: Context, private var userList : ArrayList<Profile>) : RecyclerView.Adapter<MyDetailsFragmentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_4, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtEmail: TextView = itemView.findViewById(R.id.my_email)
        private val txtHouse: TextView = itemView.findViewById(R.id.house_number)


        fun bind(user: Profile) {
            txtEmail.text = user.userEmail
            txtHouse.text = user.houseNo

        }
    }


}