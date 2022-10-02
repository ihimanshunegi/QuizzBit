package com.example.QuizzBit.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.QuizzBit.R
import com.example.QuizzBit.models.Quiz
import com.google.gson.Gson
import java.lang.StringBuilder

class result : AppCompatActivity() {
    lateinit var quiz:Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setupview()
    }

    private fun setupview() {
        val data= intent.getStringExtra("quizext")
        quiz= Gson().fromJson<Quiz>(data,Quiz::class.java)
        calculateScore()
        showans()
    }

    private fun showans() {
        val builder=StringBuilder("")
        var count=1
        for(entry in quiz.questions.entries){
            val question= entry.value
            builder.append("<font color='#18206f'><b>Question$count: ${question.description} </b></font><br></br>")
            builder.append("<font color='#009688'><b>Answer: ${question.correctAns} </b></font><br></br><br></br>")
            count++
        }
        val txtAnswer:TextView=findViewById(R.id.txtAnswer)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {

        var score=0
        for (entry in quiz.questions.entries){
            val questionn=entry.value
            if(questionn.userAnswer==questionn.correctAns){
                score+=10
            }
        }
        val scoreprint:TextView=findViewById(R.id.txtScore)
        scoreprint.text="Your Score is $score"

    }
}