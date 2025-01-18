package com.erdemyesilcicek.flokiai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.erdemyesilcicek.flokiai.navigation.NavController
import com.erdemyesilcicek.flokiai.ui.theme.FlokiAITheme
import com.erdemyesilcicek.flokiai.utils.UserInformationRepository
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.HomeStackViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Create the Firebase instances
            val db = Firebase.firestore
            val auth = Firebase.auth

            // Create the view models
            val userInformationViewModel : UserInformationViewModel = UserInformationViewModel(
                UserInformationRepository()
            )
            val categoryViewModel : CategoryViewModel = CategoryViewModel()
            val loadingViewModel : LoadingViewModel = LoadingViewModel()
            val geminiViewModel : GeminiViewModel = GeminiViewModel()
            val homeStackViewModel : HomeStackViewModel = HomeStackViewModel()

            FlokiAITheme() {
                NavController(
                    categoryViewModel,
                    userInformationViewModel,
                    loadingViewModel,
                    geminiViewModel,
                    db,
                    auth,
                    homeStackViewModel
                )
            }
        }
    }
}

