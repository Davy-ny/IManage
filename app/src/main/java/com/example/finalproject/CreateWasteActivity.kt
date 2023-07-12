package com.example.finalproject

import android.app.Activity
import android.app.ProgressDialog
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class CreateWasteActivity : AppCompatActivity() {
    lateinit var progress:ProgressDialog
    lateinit var back: CardView
    lateinit var waste_img: CircleImageView
    lateinit var waste_sub: EditText
    lateinit var btn_waste: Button
    var PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_waste)
        progress = ProgressDialog(this)
        progress.setTitle("Select successful")
        progress.setMessage("Please wait...")

        userRecyclerView = findViewById(R.id.list_view2)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()


        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManagePropertyActivity::class.java))
        }

        waste_img = findViewById(R.id.waste_add_img)
        waste_sub = findViewById(R.id.edt_waste_subject)
        btn_waste = findViewById(R.id.btn_waste_create)
        btn_waste.setOnClickListener {
            var wasteSubject = waste_sub.text.toString().trim()
            if (wasteSubject.isEmpty()){
                waste_sub.setError("Please fill this field")
                waste_sub.requestFocus()
            }else{
                var id = System.currentTimeMillis().toString()
                var ref1 = FirebaseDatabase.getInstance().getReference().child("WasteGroups/$id")
                var groupData = Rentgroup(wasteSubject,id)
                ref1.setValue(groupData)
                for (i in 0 .. (userArrayList.size - 1)){
                    progress.show()
                    var currentTime = System.currentTimeMillis().toString()
                    if (userArrayList.get(i).getIsChecked() == true){
                        var ref2 = FirebaseDatabase.getInstance().getReference().child("wstgroups/$wasteSubject/$currentTime"+i)
                        var userData = User(
                            userArrayList.get(i).firstName,
                            userArrayList.get(i).lastName,
                            userArrayList.get(i).userEmail,
                            userArrayList.get(i).profileImage,
                            userArrayList.get(i).houseNo
                        )
                        ref2.setValue(userData)
                        startActivity(Intent(this@CreateWasteActivity,WasteAdminLogin::class.java))
                    }
                }
            }
        }
        progress.dismiss()

        firebaseStore = FirebaseStorage.getInstance()
        storageRef = firebaseStore.getReference()
        waste_img.setOnClickListener{
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
                waste_img.setImageBitmap(bitmap)
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
                    userRecyclerView.adapter = MyAdapter(this@CreateWasteActivity,userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}