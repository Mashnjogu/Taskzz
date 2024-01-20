package com.example.taskzz.ui.components

import android.content.Context
import androidx.annotation.StringRes

/*
different ways to display text in the app
 */
sealed class UiText {

    data class StringText(val value: String): UiText()

    data class ResourceText(@StringRes val value: Int): UiText()
}

fun UiText.getString(context: Context): String{
    return when(this){
        is UiText.StringText -> this.value
        is UiText.ResourceText -> context.getString(this.value)
    }
}