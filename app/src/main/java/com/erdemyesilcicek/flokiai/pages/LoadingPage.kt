package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.constants.GeminiAiTalePrompt
import com.erdemyesilcicek.flokiai.datas.TaleDetails
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun LoadingPage(
    loadingViewModel: LoadingViewModel,
    geminiViewModel: GeminiViewModel,
    db: FirebaseFirestore
) {
    val prompt = GeminiAiTalePrompt(
        genre = loadingViewModel.genre,
        season = loadingViewModel.season,
        animals = loadingViewModel.animals,
        characters = loadingViewModel.characters,
        family = loadingViewModel.family,
        userInformation = loadingViewModel.userInformation
    )

    LaunchedEffect(Unit) {
        val auth = Firebase.auth
        val userId = auth.currentUser?.uid
        geminiViewModel.getGeminiData(prompt)
        geminiViewModel.responseState.collect { response ->
            response?.let { jsonString ->
                try {
                    val data: Map<String, Any> =
                        Gson().fromJson(jsonString, object : TypeToken<Map<String, Any>>() {}.type)

                    val taleDetails = TaleDetails(
                        genre = loadingViewModel.genre,
                        //genreImage = loadingViewModel.genreImage,
                        season = loadingViewModel.season,
                        animals = loadingViewModel.animals,
                        characters = loadingViewModel.characters,
                        family = loadingViewModel.family,
                        userInformation = loadingViewModel.userInformation!!
                    )

                    val taleDetailsMap: Map<String, Any> = Gson().toJson(taleDetails).let {
                        Gson().fromJson(it, object : TypeToken<Map<String, Any>>() {}.type)
                    }

                    db.collection("tales")
                        .add(data + ("TaleDetails" to taleDetailsMap) + ("userId" to userId) + ("GenreImage" to loadingViewModel.genreImage))
                        .addOnSuccessListener { documentReference ->
                            println("DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            println("Error adding document:  $e")
                        }
                } catch (e: Exception) {
                    println("Error parsing JSON or saving to Firestore $e")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LottieAnimation(animation = R.raw.bigstick)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "PLEASE WAIT!",
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "We are loading your data...",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}