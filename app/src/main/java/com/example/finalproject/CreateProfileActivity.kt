package com.example.finalproject

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class CreateProfileActivity : AppCompatActivity() {
    lateinit var myFirstName:EditText
    lateinit var myLastName:EditText
    lateinit var myEmailProfile:EditText
    lateinit var btncreate:Button
    lateinit var imageView:CircleImageView
    lateinit var progress: ProgressDialog
    var PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        myFirstName = findViewById(R.id.edtfirstname)
        myLastName = findViewById(R.id.edtlastname)
        myEmailProfile = findViewById(R.id.edt_prof_email)
        btncreate = findViewById(R.id.btn_create_profile)
        imageView = findViewById(R.id.prof_img)
        progress = ProgressDialog(this)
        progress.setTitle("Uploading")
        progress.setMessage("Please Wait...")

        firebaseStore = FirebaseStorage.getInstance()
        storageRef = firebaseStore.getReference()
        imageView.setOnClickListener{
            //OPen gallery to select an image
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select House Picture"),PICK_IMAGE_REQUEST)

            btncreate.setOnClickListener{
                var firstName = myFirstName.text.toString().trim()
                var lastName = myLastName.text.toString().trim()
                var userEmail = myEmailProfile.text.toString()
                var imageId = System.currentTimeMillis().toString()
//            mAuth = FirebaseAuth.getInstance()
                var userId = "mAuth.currentUser!!.uid"
                //Check if the user is submitting empty fields
                if(firstName.isEmpty()){
                    myFirstName.setError("Please fill this input")
                    myFirstName.requestFocus()
                }else if(lastName.isEmpty()){
                    myLastName.setError("Please fill this input")
                    myLastName.requestFocus()
                }else if(userEmail.isEmpty()){
                    myEmailProfile.setError("Please fill this input")
                    myEmailProfile.requestFocus()
                }else{
                    //proceed to upload data
                    if(filePath == null){
                        Toast.makeText(this, "Choose Image", Toast.LENGTH_SHORT).show()
                    }else{
                        var ref = storageRef.child("Profiles/$imageId")
                        progress.show()
                        ref.putFile(filePath).addOnCompleteListener {
                            progress.dismiss()
                            if (it.isSuccessful) {
                                //Proceed to store data to the db
                                ref.downloadUrl.addOnSuccessListener {
                                    var imageUrl = it.toString()
                                    var profileData = Profile(firstName,lastName,userEmail,imageUrl,imageId,userId)

                                    var dbRef = FirebaseDatabase.getInstance()
                                        .getReference().child("Profiles/$imageId")
                                    dbRef.setValue(profileData).addOnCompleteListener {
                                        if (!it.isSuccessful){
                                            Toast.makeText(applicationContext, it.exception!!.message, Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                                    Toast.makeText(applicationContext, "Upload Successful", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            } else {
                                Toast.makeText(this, it.exception!!.message,  Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
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
                imageView.setImageBitmap(bitmap)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }


}