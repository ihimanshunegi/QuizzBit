package com.example.QuizzBit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizzBit.R
import com.example.QuizzBit.models.Question

class optionAdapter(val context:Context,val question: Question): RecyclerView.Adapter<optionAdapter.optionViewHolder>() {
    private var optionlist:List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
    class optionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val option:TextView = itemView.findViewById(R.id.options)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.option,parent,false)
        return optionViewHolder(view)
    }

    override fun onBindViewHolder(holder: optionViewHolder, position: Int) {
        holder.option.text=optionlist[position]
        holder.itemView.setOnClickListener{
            question.userAnswer=optionlist[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer==optionlist[position]){
            holder.itemView.setBackgroundResource(R.drawable.ic_selection)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.ic_nonselection)
        }
    }

    override fun getItemCount(): Int = optionlist.size
}