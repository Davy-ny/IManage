package com.example.finalproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.Rentgroup

class CustomAdapter(private val context: Context, private var userSubject : ArrayList<Rentgroup>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    //Clickable recyclerview item
    private var mListener: onItemClickListener? = null
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }
    //End of clickable recyclerview

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_2, parent, false)
        return MyViewHolder(itemView,mListener)//Add listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userSubject[position]
        holder.mTxtSubjectName.text = currentItem.groupName
    }

    override fun getItemCount(): Int {
        return userSubject.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var mTxtSubjectName: TextView = itemView.findViewById(R.id.subj_name)

        //Enables clickable action/Given command
        init {
            listener?.let { listener ->
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }
}