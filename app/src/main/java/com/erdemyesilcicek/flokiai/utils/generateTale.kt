package com.erdemyesilcicek.flokiai.utils

import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.runBlocking

/*

fun generateTale() {
    val apiKey = BuildConfig.GEMINI_API_KEY
    println("Gemini API Key: $apiKey")

    val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey,
        generationConfig = GenerativeModel.GenerationConfig(
            temperature = 1f,
            topK = 40,
            topP = 0.95f,
            maxOutputTokens = 8192
        )
    )

    val chatHistory = listOf(
        content("user") {
            text(
                "Bir masal yazmanı istiyorum.\nMasal, çocuklar için uygun, eğitici ve eğlenceli olmalı. ..."
            )
        }
    )

    runBlocking {
        val chat = model.startChat(chatHistory)
        val response = chat.sendMessage("Lütfen masalı oluştur ve JSON formatında döndür.")
        println(response.text)
    }
}


 */