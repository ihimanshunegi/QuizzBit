package com.example.QuizzBit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizzBit.Adapter.optionAdapter
import com.example.QuizzBit.R
import com.example.QuizzBit.models.Question
import com.example.QuizzBit.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    var quiz:MutableList<Quiz>?= null
    var question:MutableMap<String,Question>?=null
    var index=1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFireStore()
        setEvent()

    }

    private fun setEvent() {
        val btnprevious:Button=findViewById(R.id.previous)
        val btnnext:Button=findViewById(R.id.next)
        val btnsubmit:Button=findViewById(R.id.submit)
        btnnext.setOnClickListener{
            index++
            bindviews()
        }
        btnprevious.setOnClickListener{
            index--
            bindviews()
        }
        btnsubmit.setOnClickListener{
            Log.d("Submit",question.toString())
            val intent=Intent(this,result::class.java)
            val json= Gson().toJson(quiz!![0])
            intent.putExtra("quizext",json)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpFireStore() {
        val date= intent.getStringExtra("Date")
        if(date!=null){
            val firestore:FirebaseFirestore= FirebaseFirestore.getInstance()
            firestore.collection("quizzes").whereEqualTo("title", date)

                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quiz = it.toObjects(Quiz::class.java)
                        question = quiz!![0].questions
                        bindviews()
                    }

                }

        }

    }

    private fun bindviews() {
        val btnprevious:Button=findViewById(R.id.previous)
        val btnnext:Button=findViewById(R.id.next)
        val btnsubmit:Button=findViewById(R.id.submit)

        btnnext.visibility= View.GONE
        btnprevious.visibility= View.GONE
        btnsubmit.visibility= View.GONE
        if(index==1){
            btnnext.visibility= View.VISIBLE
        }
        else if (index== question!!.size ){
            btnprevious.visibility= View.VISIBLE
            btnsubmit.visibility= View.VISIBLE
        }
        else{
            btnnext.visibility= View.VISIBLE
            btnprevious.visibility= View.VISIBLE
        }
        val question= question!!["Question$index"]
        question?.let {
            val description:TextView=findViewById(R.id.description)
            description.text=it.description
            val optionadapter=optionAdapter(this,it)
            val recyclerView:RecyclerView=findViewById(R.id.quesrecycler)
            recyclerView.layoutManager=LinearLayoutManager(this)
            recyclerView.adapter=optionadapter
            recyclerView.setHasFixedSize(true)

        }

    }

}