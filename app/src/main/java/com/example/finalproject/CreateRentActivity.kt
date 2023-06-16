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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class CreateRentActivity : AppCompatActivity() {
    lateinit var back:CardView
    lateinit var rent_img:CircleImageView
    lateinit var rent_sub:EditText
    lateinit var btn_rent:Button
    var PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_rent)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManagePropertyActivity::class.java))
        }

        rent_img = findViewById(R.id.rent_add_img)
        rent_sub = findViewById(R.id.edt_rent_subject)
        btn_rent = findViewById(R.id.btn_rent_create)

        firebaseStore = FirebaseStorage.getInstance()
        storageRef = firebaseStore.getReference()
        rent_img.setOnClickListener{
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
                rent_img.setImageBitmap(bitmap)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}