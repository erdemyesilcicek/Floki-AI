package com.erdemyesilcicek.flokiai.utils

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
fun generateTale(prompt: String) {
    // API client'ı başlatıyoruz
    val apiKey = "YOUR_API_KEY" // Burada API anahtarınızı kullanın
    val generativeAiClient = GenerativeAiClient.create(apiKey)

    try {
        // API'ye promptu gönderip yanıtı alıyoruz
        val response = withContext(Dispatchers.IO) {
            // 'generate' metodu promptu alır ve yanıt döner
            generativeAiClient.generate(prompt)
        }

        // Yanıtı logcat'e basıyoruz
        Log.d("GeminiAPI", "Generated Response: ${response.text}")
    } catch (e: Exception) {
        // Hata durumunda loglama
        Log.e("GeminiAPI", "Error generating response: ${e.message}")
    }
}


 */