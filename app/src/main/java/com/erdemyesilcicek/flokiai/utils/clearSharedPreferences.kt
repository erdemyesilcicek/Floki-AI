package com.erdemyesilcicek.flokiai.utils

import android.content.Context

fun clearSharedPreferences(context: Context) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}
