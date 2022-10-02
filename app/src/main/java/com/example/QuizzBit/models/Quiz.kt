package com.example.QuizzBit.models
//this data class is created for holding the data of quiz from firestore.
// we can store the title of diff questions(here diff dates ) and data of diff dates()
data class Quiz(
    var id:String="",
    var title:String="",
    var questions:MutableMap<String,Question> = mutableMapOf()
)
