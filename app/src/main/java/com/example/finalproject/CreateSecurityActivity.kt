package com.example.finalproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class CreateSecurityActivity : AppCompatActivity() {
    lateinit var back: CardView
    lateinit var security_img: CircleImageView
    lateinit var security_sub: EditText
    lateinit var btn_security: Button
    var PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_security)

        userRecyclerView = findViewById(R.id.list_view1)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()


        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManagePropertyActivity::class.java))
        }

        security_img = findViewById(R.id.sec_add_img)
        security_sub = findViewById(R.id.edt_sec_subject)
        btn_security = findViewById(R.id.btn_sec_create)

        firebaseStore = FirebaseStorage.getInstance()
        storageRef = firebaseStore.getReference()
        security_img.setOnClickListener{
            //OPen gallery to select an image
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select House Picture"),PICK_IMAGE_REQUEST)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null){
                return
            }
            filePath = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                security_img.setImageBitmap(bitmap)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Profiles")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = MyAdapter(this@CreateSecurityActivity,userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}