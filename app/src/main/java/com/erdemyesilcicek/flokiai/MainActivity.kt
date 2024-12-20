package com.erdemyesilcicek.flokiai

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erdemyesilcicek.flokiai.components.TaleCard
import com.erdemyesilcicek.flokiai.datas.Tale
import com.erdemyesilcicek.flokiai.navigation.NavController
import com.erdemyesilcicek.flokiai.pages.CreateTalePage
import com.erdemyesilcicek.flokiai.pages.GetStartedPage
import com.erdemyesilcicek.flokiai.pages.HomePage
import com.erdemyesilcicek.flokiai.pages.LoadingPage
import com.erdemyesilcicek.flokiai.pages.OptionsPage
import com.erdemyesilcicek.flokiai.pages.ReadTalePage
import com.erdemyesilcicek.flokiai.pages.SignInPage
import com.erdemyesilcicek.flokiai.pages.SignUpPage
import com.erdemyesilcicek.flokiai.pages.optionspages.Feedback
import com.erdemyesilcicek.flokiai.pages.optionspages.UserInformation
import com.erdemyesilcicek.flokiai.ui.theme.FlokiAITheme
import com.erdemyesilcicek.flokiai.utils.UserInformationRepository
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userInformationViewModel : UserInformationViewModel = UserInformationViewModel(
                UserInformationRepository(
                    getSharedPreferences(
                        "character_info",
                        MODE_PRIVATE
                    )
                )
            )
            val db = Firebase.firestore
            val authViewModel : AuthViewModel = AuthViewModel()
            val categoryViewModel : CategoryViewModel = CategoryViewModel()
            val loadingViewModel : LoadingViewModel = LoadingViewModel()
            val geminiViewModel : GeminiViewModel = GeminiViewModel()

            FlokiAITheme() {
                NavController(
                    authViewModel,
                    categoryViewModel,
                    userInformationViewModel,
                    loadingViewModel,
                    geminiViewModel,
                    db
                )
            }
        }
    }
}

