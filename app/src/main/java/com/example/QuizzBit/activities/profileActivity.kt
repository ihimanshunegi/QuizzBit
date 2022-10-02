package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.QuizzBit.R
import com.google.firebase.auth.FirebaseAuth

class profileActivity : AppCompatActivity() {
    //Here we are using firebaseauth for getting email id of user
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth= FirebaseAuth.getInstance()

        val email:TextView=findViewById(R.id.email)
        email.text= firebaseAuth.currentUser?.email

        val logbtn:Button=findViewById(R.id.btnlogout)
        //logout button functionality (profile activity to login activity)
        logbtn.setOnClickListener {
            // to get signout so there will be no current user
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}