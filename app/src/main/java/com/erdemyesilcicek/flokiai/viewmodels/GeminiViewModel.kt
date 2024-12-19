package com.erdemyesilcicek.flokiai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdemyesilcicek.flokiai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class GeminiViewModel : ViewModel() {

    fun getGeminiData(prompt: String) {
        viewModelScope.launch {
            try {
                println("api Key: ${BuildConfig.API_KEY}")
                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = BuildConfig.API_KEY
                )
                val response = generativeModel.generateContent(prompt)
                println(response.text)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}