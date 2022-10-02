package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizzBit.Adapter.Quizadapter
import com.example.QuizzBit.R
import com.example.QuizzBit.models.Quiz
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    lateinit var myAdapter: Quizadapter
    private var quizlist= mutableListOf<Quiz>()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpviews()
        setupDatePicker()
        setUpdrawerlayout()
    }


    private fun setupDatePicker() {
        val btn:FloatingActionButton=findViewById(R.id.datepicker)
        btn.setOnClickListener{
            val datePicker:com.google.android.material.datepicker.MaterialDatePicker<Long> = com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"Date Picker")

            datePicker.addOnPositiveButtonClickListener {
                val dateFormat=SimpleDateFormat("dd-mm-yyyy")
                val date:String=dateFormat.format(Date(it))
                val intent=Intent(this,QuestionActivity::class.java)
                intent.putExtra("Date",date)
                startActivity(intent)
            }

            datePicker.addOnNegativeButtonClickListener {
                Log.d("Date Picker",datePicker.headerText)
            }

            datePicker.addOnCancelListener {
                Log.d("Date Picker","Canceled")
            }
        }
    }

    private fun setUpviews() {

        setupFirestore()
        setUprecyclerView()
        setUpdrawerlayout()
    }

    private fun setupFirestore() {
        firestore= FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference=firestore.collection("quizzes")
        collectionReference.orderBy("title",Query.Direction.ASCENDING).addSnapshotListener { value, error ->
            if(value==null|| error!=null){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            Log.d("myDATA",value.toObjects(Quiz::class.java).toString())
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            myAdapter.notifyDataSetChanged()
        }
    }

    private fun setUprecyclerView() {
        myAdapter = Quizadapter(this,quizlist)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager=GridLayoutManager(this,2)
        recyclerView.adapter=myAdapter
    }

    private fun setUpdrawerlayout() {

        val appBar:Toolbar=findViewById(R.id.topAppBar)
        setSupportActionBar(appBar)
        val drawerLayout:DrawerLayout=findViewById(R.id.drawer)
        actionBarDrawerToggle=ActionBarDrawerToggle(this,drawerLayout, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()

        val navView:NavigationView=findViewById(R.id.navigationview)
        navView.setNavigationItemSelectedListener {
            val intent=Intent(this,profileActivity::class.java)
            startActivity(intent)
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


