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
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel : AuthViewModel = AuthViewModel()
            val categoryViewModel : CategoryViewModel = CategoryViewModel()
            val userInformationViewModel = UserInformationViewModel(UserInformationRepository(getSharedPreferences("character_info", MODE_PRIVATE)))
            val loadingViewModel = LoadingViewModel()

            FlokiAITheme() {
                NavController(authViewModel, categoryViewModel, userInformationViewModel, loadingViewModel)
            }
        }
    }
}

@Composable
fun NavController(authViewModel: AuthViewModel, categoryViewModel: CategoryViewModel, userInformationViewModel: UserInformationViewModel, loadingViewModel: LoadingViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "GetStartedPage"
    ) {
        composable(route = "HomePage") { HomePage(navController) }

        composable(route = "LoadingPage") { LoadingPage(loadingViewModel)}

        composable(route = "CreateTalePage") { CreateTalePage(navController, categoryViewModel, userInformationViewModel, loadingViewModel) }

        composable(route = "OptionsPage") { OptionsPage(navController) }

        composable(route = "UserInformation") { UserInformation(navController, context, userInformationViewModel) }

        composable(route = "Feedback") { Feedback(navController) }

        composable(route = "GetStartedPage") { GetStartedPage(navController, authViewModel) }

        composable(route = "SignUpPage") { SignUpPage(navController, authViewModel) }

        composable(route = "SignInPage") { SignInPage(navController, authViewModel) }

        composable(route = "ReadTalePage" + "?id={id}", arguments = listOf(navArgument("id") {
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