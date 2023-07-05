package com.example.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val context: Context, private var userSubject : ArrayList<Rentgroup>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_2,parent,false)
        return CustomAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = userSubject[position]
        holder.mTxtSubjectName.text = currentItem.groupName
    }

    override fun getItemCount(): Int {
        return userSubject.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var mTxtSubjectName: TextView = itemView.findViewById(R.id.subj_name)
    }
}