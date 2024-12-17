package com.erdemyesilcicek.flokiai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erdemyesilcicek.flokiai.components.TaleCard
import com.erdemyesilcicek.flokiai.datas.Tale
import com.erdemyesilcicek.flokiai.pages.CreateTalePage
import com.erdemyesilcicek.flokiai.pages.GetStartedPage
import com.erdemyesilcicek.flokiai.pages.HomePage
import com.erdemyesilcicek.flokiai.pages.OptionsPage
import com.erdemyesilcicek.flokiai.pages.ReadTalePage
import com.erdemyesilcicek.flokiai.pages.SignInPage
import com.erdemyesilcicek.flokiai.pages.SignUpPage
import com.erdemyesilcicek.flokiai.pages.optionspages.Feedback
import com.erdemyesilcicek.flokiai.pages.optionspages.UserInformation
import com.erdemyesilcicek.flokiai.ui.theme.FlokiAITheme
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel : AuthViewModel = AuthViewModel()

            FlokiAITheme() {
                NavController(authViewModel)
            }
        }
    }
}

@Composable
fun NavController(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "GetStartedPage"
    ) {
        composable(route = "HomePage") { HomePage(navController) }

        composable(route = "CreateTalePage") { CreateTalePage(navController) }

        composable(route = "OptionsPage") { OptionsPage(navController) }

        composable(route = "UserInformation") { UserInformation(navController) }

        composable(route = "Feedback") { Feedback(navController) }

        composable(route = "GetStartedPage") { GetStartedPage(navController) }

        composable(route = "SignUpPage") { SignUpPage(navController, authViewModel) }

        composable(route = "SignInPage") { SignInPage(navController, authViewModel) }

        composable("ReadTalePage" + "?id={id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
            defaultValue = -1
        })) {
            val id = it.arguments?.getInt("id")!!
            ReadTalePage(navController, id)
        }
        composable(route = "TaleCard") {
            TaleCard(navController, card = Tale(5, "", "", 0, ""))
        }
    }
}