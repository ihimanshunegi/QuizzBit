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

class Signup : AppCompatActivity() {

    lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth= FirebaseAuth.getInstance()
        //click listener for signup Button
        val signupbtn=findViewById<Button>(R.id.btnsignup)
        signupbtn.setOnClickListener {
            signupuser() //function call for signup
        }
        //button for redirecting to login screen(if user already has account)
        val splogin=findViewById<TextView>(R.id.login)
        splogin.setOnClickListener {
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
    //function definition for signup user
    private fun signupuser(){
        val etemail=findViewById<EditText>(R.id.editTextTextEmailAddressSp)
        val email:String= etemail.text.toString() //taking string from user input value in edittext for email

        val etpass=findViewById<EditText>(R.id.editTextPasswordSp)
        val Pass:String= etpass.text.toString()  //taking string from user input value in edittext for password

        val etconpass=findViewById<EditText>(R.id.editTextconfirmPasswordSp)
        val confirmPass:String= etconpass.text.toString()  //taking string from user input value in edittext for confirming password

        //if email, password, confirm password is blank then we have to show a toast and return
        if(email.isBlank()|| Pass.isBlank()||confirmPass.isBlank()){
            Toast.makeText(this,"Email and Password can't be Blank",Toast.LENGTH_SHORT).show()
            return
        }
        //if password and confirm password is not matched
        if(Pass!=confirmPass){
            Toast.makeText(this,"Passwords don't matched.",Toast.LENGTH_SHORT).show()
            return
        }

        //creating new user
        firebaseAuth.createUserWithEmailAndPassword(email,Pass)
            .addOnCompleteListener(this){
                // if signup was successful
                if (it.isSuccessful){
                    Toast.makeText(this,"Signup Successfully",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                //else
                else{
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                }
            }

    }
}