package com.example.finalproject

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R.id
import com.example.finalproject.databinding.ActivityCreateRentBinding
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

class CreateRentActivity : AppCompatActivity() {
    lateinit var back:CardView
    private lateinit var rent_img:CircleImageView
    private lateinit var rent_sub:EditText
    private lateinit var btn_rent:Button
    var PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog
    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList:ArrayList<User>
    private lateinit var binding:ActivityCreateRentBinding
    private lateinit var adapter:MyAdapter
    private lateinit var myCheckBox:CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_rent)
        progress = ProgressDialog(this)
        progress.setTitle("Select successful")
        progress.setMessage("Please wait...")

        //Searching start
        binding = ActivityCreateRentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerView = binding.listView1
        userArrayList = ArrayList()
        adapter = MyAdapter(this@CreateRentActivity, userArrayList)


        userRecyclerView = findViewById(id.list_view1)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()

        //Exiting page
        back = findViewById(id.arrow_back)
        back.setOnClickListener{
            startActivity(Intent(applicationContext,ManagePropertyActivity::class.java))
        }
    //Creating group
        rent_img = findViewById(id.rent_add_img)
        rent_sub = findViewById(id.edt_rent_subject)
        btn_rent = findViewById(id.btn_rent_create)
        btn_rent.setOnClickListener {
            var rentSubject = rent_sub.text.toString().trim()
            if (rentSubject.isEmpty()){
                rent_sub.setError("Please fill this field")
                rent_sub.requestFocus()
            }else{
                var id = System.currentTimeMillis().toString()
                var ref1 = FirebaseDatabase.getInstance().getReference().child("Groups/$id")
                var groupData = Rentgroup(rentSubject,id)
                ref1.setValue(groupData)
                for (i in 0 .. (userArrayList.size - 1)){
                    progress.show()
                    var currentTime = System.currentTimeMillis().toString()
                    if (userArrayList.get(i).getIsChecked() == true){
                        var ref2 = FirebaseDatabase.getInstance().getReference().child("Rentgroups/$rentSubject/$currentTime"+i)
                        var userData = User(
                            userArrayList.get(i).firstName,
                            userArrayList.get(i).lastName,
                            userArrayList.get(i).userEmail,
                            userArrayList.get(i).profileImage,
                            userArrayList.get(i).houseNo
                        )
                        ref2.setValue(userData)
                        startActivity(Intent(this@CreateRentActivity,RentAdminLogin::class.java))
                    }
                }
            }
        }
        progress.dismiss()


        //Adding image
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
    //Part of retrieving image
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


    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Profiles")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = MyAdapter(this@CreateRentActivity,userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }

        })
    }
    fun searchList(text: String){
        val searchList = ArrayList<User>()
        for (dataClass in userArrayList){
            if(dataClass.userEmail.lowercase().contains(text.lowercase())){
                searchList.add(dataClass)
            }
        }
        adapter.searchDataList(searchList)
    }
}