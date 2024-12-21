package com.erdemyesilcicek.flokiai.navigation

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
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavController(
    authViewModel: AuthViewModel,
    categoryViewModel: CategoryViewModel,
    userInformationViewModel: UserInformationViewModel,
    loadingViewModel: LoadingViewModel,
    geminiViewModel: GeminiViewModel,
    db : FirebaseFirestore
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "GetStartedPage"
    ) {
        composable(route = "HomePage") { HomePage(navController) }

        composable(route = "LoadingPage") { LoadingPage(loadingViewModel, geminiViewModel,db) }

        composable(route = "CreateTalePage") {
            CreateTalePage(
                navController,
                categoryViewModel,
                userInformationViewModel,
                loadingViewModel
            )
        }

        composable(route = "OptionsPage") { OptionsPage(navController) }

        composable(route = "UserInformation") {
            UserInformation(
                navController,
                userInformationViewModel
            )
        }

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
            TaleCard(navController, tale = Tale(5, "", "", 0, ""))
        }
    }
}