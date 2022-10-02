package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.QuizzBit.R

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        /*
        {  if app is launching first time after installation then LoginIntro activity will be called
        else we will call splash screen }

       ==> Preference cannot be modified without using SharedPreference.editor after getting set
           Created a instance of class getSharedPreferences to use its methods.

         */
        val pref = getSharedPreferences("Preferences", MODE_PRIVATE)
        /*made boolean variable to store result accordingly .
        app is launching for very first time after intalling then here we have set value of boolean var/val as true
        */
        var FirstTime= pref.getBoolean("firstStart",true)
        if(FirstTime==true){

            //if app is started for first time then show LoginIntro activity
            val intent= Intent(this,LoginIntro::class.java)
            startActivity(intent)
            finish()

            //created a instance of editor
            val editor=pref.edit()
            /*now app is started for first time so modify the value of boolean to false using editor
            if the data is changed, it can only be changed using instance of editor
             */
            editor.putBoolean("firstStart",false)
            editor.apply()

        }
        else {
            //else show splash screen
                //handler is used for using its method postdelayed to hold splash screen for a value of passed parameter of time .
            val handler: Handler = Handler()
            handler.postDelayed({ intentcall() }, 3000)
        }


    }

    //function for calling  login screen after splash screen
    private fun intentcall() {
       val intent=Intent(this,Login::class.java)
        startActivity(intent)
        finish()
    }
}