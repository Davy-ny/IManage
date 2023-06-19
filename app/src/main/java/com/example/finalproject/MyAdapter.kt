package com.example.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val context: Context,private val userList : ArrayList<User>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = userList[position]
        holder.mTxtFirstName.text = currentItem.firstName
        holder.mTxtLastName.text = currentItem.lastName
        holder.mTxtEmail.text = currentItem.userEmail
        Glide.with(context).load(currentItem.profileImage).into(holder.imgPhoto)
    }

    class MyViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        var mTxtFirstName: TextView = itemView.findViewById(R.id.first_name)
        var mTxtLastName: TextView = itemView.findViewById(R.id.last_name)
        var mTxtEmail: TextView = itemView.findViewById(R.id.my_email)
        var imgPhoto:CircleImageView = itemView.findViewById(R.id.photo)

    }
}