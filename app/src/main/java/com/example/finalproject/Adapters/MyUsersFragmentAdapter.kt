package com.example.finalproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.User
import de.hdodenhof.circleimageview.CircleImageView

class MyUsersFragmentAdapter(private var context:Context, private var userList : ArrayList<User>) : RecyclerView.Adapter<MyUsersFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_3, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = userList[position]
            holder.bind(user)
        }

        override fun getItemCount(): Int {
            return userList.size
        }


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val txtFirst: TextView = itemView.findViewById(R.id.first_name)
            private val txtLast: TextView = itemView.findViewById(R.id.last_name)
            private val txtEmail: TextView = itemView.findViewById(R.id.my_email)
            private val txtPhoto: CircleImageView = itemView.findViewById(R.id.photo)

            fun bind(user: User) {
                txtFirst.text = user.firstName
                txtLast.text = user.lastName
                txtEmail.text = user.userEmail
                Glide.with(context).load(user.profileImage).into(txtPhoto)
            }
}
}