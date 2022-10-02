package com.example.QuizzBit.utils

object colorpicker {
    val colors= arrayOf("#f94144","#f3722c","#f8961e","#f9844a","#f9c74f","#90be6d","#43aa8b","#4d908e","#577590","#277da1")
    var currentcolor=0
    fun getcolor():String{
        currentcolor= (currentcolor+1)% colors.size
        return colors[currentcolor]
    }
}