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
import com.erdemyesilcicek.flokiai.pages.HomePage
import com.erdemyesilcicek.flokiai.pages.LoginPage
import com.erdemyesilcicek.flokiai.pages.OptionsPage
import com.erdemyesilcicek.flokiai.pages.ReadTalePage
import com.erdemyesilcicek.flokiai.pages.optionspages.Feedback
import com.erdemyesilcicek.flokiai.pages.optionspages.UserInformation
import com.erdemyesilcicek.flokiai.ui.theme.FlokiAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlokiAITheme {
                NavController()
            }
        }
    }
}

@Composable
fun NavController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "LoginPage"
    ) {
        composable(route = "HomePage") {
            HomePage(navController)
        }
        composable(route = "CreateTalePage") {
            CreateTalePage(navController)
        }
        composable(route = "OptionsPage") {
            OptionsPage(navController)
        }
        composable(route = "UserInformation") {
            UserInformation(navController)
        }
        composable(route = "Feedback") {
            Feedback(navController)
        }
        composable(route = "LoginPage") {
            LoginPage(navController)
        }

        composable("ReadTalePage" + "?id={id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
            defaultValue = -1
        })) {
            val id = it.arguments?.getInt("id")!!
            ReadTalePage(navController, id)
        }
        composable(route = "TaleCard") {
            TaleCard(navController, card = Tale(5, "", "", 0,""))
        }
    }
}