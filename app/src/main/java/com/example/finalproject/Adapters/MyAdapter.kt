package com.example.finalproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.User
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val context: Context,private var userList : ArrayList<User>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size

    }

    fun searchDataList(searchList: ArrayList<User>){
        userList = searchList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = userList[position]
        holder.mTxtFirstName.text = currentItem.firstName
        holder.mTxtLastName.text = currentItem.lastName
        holder.mTxtEmail.text = currentItem.userEmail
        holder.mTxtHouseNumber.text = currentItem.houseNo
        Glide.with(context).load(currentItem.profileImage).into(holder.imgPhoto)
        holder.myCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            currentItem.setIsChecked(isChecked)
        }
    }

    class MyViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        var mTxtFirstName: TextView = itemView.findViewById(R.id.first_name)
        var mTxtLastName: TextView = itemView.findViewById(R.id.last_name)
        var mTxtEmail: TextView = itemView.findViewById(R.id.my_email)
        var mTxtHouseNumber: TextView = itemView.findViewById(R.id.house_number)

        var imgPhoto:CircleImageView = itemView.findViewById(R.id.photo)
        var myCheckBox:CheckBox = itemView.findViewById(R.id.myCheckBox)

    }
}