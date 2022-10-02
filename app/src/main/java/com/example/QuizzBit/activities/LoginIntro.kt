package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.QuizzBit.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

        val auth:FirebaseAuth= FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
           //if already logged in
            redirected("ALREADY")
        }

        //when user will click on get started then we will redirect them to login screen
        val btnintro=findViewById<Button>(R.id.btnintro)
        btnintro.setOnClickListener {
            redirected("LOGIN")
        }
    }

    //created a funtion in which we will pass which are set to perform different action..
    private fun redirected(name:String) {
        // when fun is used to store diff values to variable according to passed condition.
        val intent=when(name){
            "LOGIN"->Intent(this, Login::class.java)
            "ALREADY"->Intent(this, MainActivity::class.java)
            else-> throw Exception("No Path Exist")
        }
        startActivity(intent)
        finish()
    }
}