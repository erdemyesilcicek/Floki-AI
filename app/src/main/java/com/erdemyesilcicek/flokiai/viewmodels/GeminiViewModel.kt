package com.erdemyesilcicek.flokiai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdemyesilcicek.flokiai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeminiViewModel : ViewModel() {
    private val _responseState = MutableStateFlow<String?>(null)
    val responseState: StateFlow<String?> = _responseState

    fun getGeminiData(prompt: String) {
        viewModelScope.launch {
            try {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = BuildConfig.API_KEY,
                    generationConfig = generationConfig {
                        temperature = 1f
                        topK = 40
                        topP = 0.95f
                        maxOutputTokens = 8192
                        responseMimeType = "application/json"
                    }

                )
                val response = generativeModel.generateContent(prompt)
                _responseState.value = response.text

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}