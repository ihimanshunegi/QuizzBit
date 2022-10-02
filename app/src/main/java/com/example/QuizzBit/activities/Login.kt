package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.QuizzBit.R
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    //declared variable of type firebaseauth
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkuser() //if user is already loggedin then he/she will be redirected to main activity
        firebaseAuth= FirebaseAuth.getInstance()

        val loginbtn=findViewById<Button>(R.id.btnlogin)  //login button
        loginbtn.setOnClickListener {
            loginuser()
        }
        val lgsignupbtn=findViewById<TextView>(R.id.signup) // signup activity
        lgsignupbtn.setOnClickListener {
            val intent= Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun checkuser() {
        firebaseAuth = FirebaseAuth.getInstance()
        val email: String? = firebaseAuth.currentUser?.email
        if (email != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun loginuser(){
        val lgemail=findViewById<EditText>(R.id.editTextTextEmailAddress)
        val email:String=lgemail.text.toString()

        val lgpass=findViewById<EditText>(R.id.editTextTextPassword)
        val pass:String=lgpass.text.toString()
        //creating toast if editText block is empty
        if(email.isBlank()||pass.isBlank()){
            Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(){
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Authentication Error",Toast.LENGTH_SHORT).show()
                }
            }
    }
}