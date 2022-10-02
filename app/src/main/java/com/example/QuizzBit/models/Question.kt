package com.example.QuizzBit.models
//Data class is used for holding data. It is a special type of classing which made for dealing with data
//it is unusual to create a class for only data holding purpose.
data class Question(
    var description:String="",
    var option1:String="",
    var option2:String="",
    var option3:String="",
    var option4:String="",
    var correctAns:String="",
    var userAnswer:String=""
)
