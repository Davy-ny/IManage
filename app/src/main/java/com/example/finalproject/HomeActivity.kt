package com.example.finalproject

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.databinding.NavHeaderBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: NavHeaderBinding
    private lateinit var profile: Profile
    private lateinit var uid: String
    lateinit var dbRef: DatabaseReference
    lateinit var dialog: Dialog
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageRef: StorageReference
    lateinit var progress: ProgressDialog
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var payment: CardView
    lateinit var manage: CardView
    lateinit var create: TextView
    lateinit var list: CardView

    //lateinit var mydrawerLayout: DrawerLayout
    // lateinit var navView: NavigationView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Retrieving profile info
        mAuth = FirebaseAuth.getInstance()
        //uid = mAuth.currentUser?.uid.toString()

        /*dbRef = FirebaseDatabase.getInstance().getReference("Profiles")
        if (uid.isNotEmpty()) {
             getUserData()
        }

         */



        progress = ProgressDialog(this)
        progress.setTitle("Logout Successful")
        progress.setMessage("Please Wait...")

        // binding = NavHeaderBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        val mydrawerLayout: DrawerLayout = findViewById(R.id.my_drawer)
        val navView: NavigationView = findViewById(R.id.nav_view)

        list = findViewById(R.id.list_drawout)
        list.setOnClickListener {
            mydrawerLayout.openDrawer(navView)
        }


        toggle = ActionBarDrawerToggle(this, mydrawerLayout, R.string.open, R.string.close)
        mydrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, CreateProfileActivity::class.java))
                R.id.nav_sync -> Toast.makeText(
                    applicationContext,
                    "Clicked Sync",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_trash -> Toast.makeText(
                    applicationContext,
                    "Clicked Delete",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_setting -> Toast.makeText(
                    applicationContext,
                    "Clicked Setting",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_logout -> {sendToLogin()
                Toast.makeText(applicationContext, "Logout Successful", Toast.LENGTH_SHORT).show() }
                R.id.nav_share -> Toast.makeText(
                    applicationContext,
                    "Clicked Share",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_rate_us -> Toast.makeText(
                    applicationContext,
                    "Clicked Rate us",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true

        }

        payment = findViewById(R.id.make_payment)
        payment.setOnClickListener {
            startActivity(Intent(this, MakePaymentActivity::class.java))
        }

        manage = findViewById(R.id.manage_property)
        manage.setOnClickListener {
            startActivity(Intent(this, ManagePropertyActivity::class.java))
        }
    }
    //Remove after debug

    /*private fun getUserData() {
        showProgressBar()
        dbRef.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                profile = snapshot.getValue(Profile::class.java)!!
                binding.myFirst.setText(profile.firstName + "" + profile.lastName)
                binding.myUserEmail.setText(profile.userEmail)
                getUserProfile()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
                Toast.makeText(this@HomeActivity, "Failed to get user profile data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getUserProfile() {
        storageRef = FirebaseStorage.getInstance().getReference().child("Profiles/$uid")
        val localFile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localFile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.profileImg.setImageBitmap(bitmap)
            hideProgressBar()
            
        }.addOnFailureListener{

            hideProgressBar()
            Toast.makeText(this@HomeActivity, "Failed to retrieve image", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }*/


    private fun sendToLogin() {
        mAuth.signOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}

    /*private fun showProgressBar(){
        dialog = Dialog(this@HomeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }
}
*/