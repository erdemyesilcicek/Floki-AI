package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class HomeStackViewModel : ViewModel() {
    var stack = mutableIntStateOf(1)

    fun changeStack(){
        stack.intValue = if (stack.intValue == 1) 0 else 1
    }
}