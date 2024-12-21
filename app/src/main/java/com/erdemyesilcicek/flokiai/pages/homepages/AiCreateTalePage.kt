package com.erdemyesilcicek.flokiai.pages.homepages

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.components.AiTaleCard
import com.erdemyesilcicek.flokiai.datas.AiTale
import com.erdemyesilcicek.flokiai.datas.TaleDetails
import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun AiCreateTalePage(navController: NavController) {
    val db = Firebase.firestore
    val userId = Firebase.auth.currentUser?.uid
    val aiTales = remember { mutableStateOf(listOf<AiTale>()) }

    LaunchedEffect(Unit) {
        userId?.let {
            db.collection("tales")
                .whereEqualTo("userId", it)
                .get()
                .addOnSuccessListener { documents ->
                    val talesList = documents.mapNotNull { document ->
                        try {
                            // TaleDetails nesnesini al ve dönüştür
                            val taleDetailsMap = document.data["TaleDetails"] as? Map<String, Any>
                            val taleDetails = taleDetailsMap?.let {
                                TaleDetails(
                                    genre = it["genre"] as? String ?: "",
                                    //genreImage = it["genreImage"] as? Int ?: 2131165331,
                                    season = it["season"] as? String ?: "",
                                    animals = it["animals"] as? List<String> ?: listOf(),
                                    characters = it["characters"] as? List<String> ?: listOf(),
                                    family = it["family"] as? List<String> ?: listOf(),
                                    userInformation = it["userInformation"] as? UserInformationModel
                                        ?: UserInformationModel()
                                )
                            } ?: TaleDetails() // Varsayılan bir nesne döndür

                            val taleItself = document.data.get("TaleItself").toString()
                            val taleTitle = document.data.get("TaleTitle").toString()
                            val taleSummary = document.data.get("TaleSummary").toString()
                            val UserId = document.data.get("userId").toString()
                            val genreImage = document.data.get("GenreImage").toString().toInt()

                            // AiTale nesnesi oluştur
                            AiTale(
                                TaleDetails = taleDetails,
                                TaleItself = taleItself,
                                TaleTitle = taleTitle,
                                TaleSummary = taleSummary,
                                userId = UserId,
                                GenreImage = genreImage
                            )
                        } catch (e: Exception) {
                            Log.e(TAG, "Error mapping document: ${e.localizedMessage}")
                            null
                        }
                    }
                    aiTales.value = talesList
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting documents: $exception")
                }
        } ?: run {
            Log.e(TAG, "User ID is null")
            return@LaunchedEffect
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AI Created",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        if (aiTales.value.isEmpty()) {
            Text("No tales have been created yet.")
        } else {
            LazyColumn {
                itemsIndexed(aiTales.value) { index, aiTale ->
                    AiTaleCard(navController, aiTale)
                }
            }
        }
    }
}