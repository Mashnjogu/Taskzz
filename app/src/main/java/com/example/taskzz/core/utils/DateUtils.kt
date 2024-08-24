package com.example.taskzz.core.utils

import java.time.LocalDate

fun LocalDate.getSuffixForDateOfMonth(): String{
    val dayOfMonth = this.dayOfMonth

    return when(dayOfMonth){
        in 11..13 -> "th"
        else -> {
            when(dayOfMonth % 10){
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        }
    }
}