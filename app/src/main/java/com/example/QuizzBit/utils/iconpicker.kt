package com.example.QuizzBit.utils

import com.example.QuizzBit.R

object iconpicker {
    val icon= arrayOf(R.drawable.ic_icon2,R.drawable.ic_icon3,R.drawable.ic_icon4,R.drawable.ic_icon5,R.drawable.ic_icon6,R.drawable.ic_icon7)
    var currenticon=0
    fun geticon():Int{
        currenticon= (currenticon+1)% icon.size
        return icon[currenticon]
    }
}