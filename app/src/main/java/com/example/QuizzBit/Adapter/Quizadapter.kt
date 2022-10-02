package com.example.QuizzBit.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizzBit.R
import com.example.QuizzBit.activities.QuestionActivity
import com.example.QuizzBit.models.Quiz
import com.example.QuizzBit.utils.colorpicker
import com.example.QuizzBit.utils.iconpicker

class Quizadapter(val context: Context,val Quizlist:List<Quiz>): RecyclerView.Adapter<MyviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view=LayoutInflater.from(context)
            .inflate(R.layout.quiz_item,parent,false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        holder.date.text= Quizlist[position].title
        holder.cardcontainer.setCardBackgroundColor(Color.parseColor(colorpicker.getcolor()))
        holder.icon.setImageResource(iconpicker.geticon())
        holder.itemView.setOnClickListener(){
            Toast.makeText(context, Quizlist[position].title, Toast.LENGTH_SHORT).show()
            val intent= Intent(context,QuestionActivity::class.java)
            intent.putExtra("Date",Quizlist[position].title)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return Quizlist.size
    }
}
class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var date:TextView = itemView.findViewById(R.id.itemTextView)
    var icon:ImageView = itemView.findViewById(R.id.itemImageView)
    var cardcontainer:CardView=itemView.findViewById(R.id.cardview)
}
