package com.erdemyesilcicek.flokiai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erdemyesilcicek.flokiai.components.TaleCard
import com.erdemyesilcicek.flokiai.datas.Tale
import com.erdemyesilcicek.flokiai.pages.AiReadTalePage
import com.erdemyesilcicek.flokiai.pages.CreateTalePage
import com.erdemyesilcicek.flokiai.pages.authpages.GetStartedPage
import com.erdemyesilcicek.flokiai.pages.HomePage
import com.erdemyesilcicek.flokiai.pages.LoadingPage
import com.erdemyesilcicek.flokiai.pages.OptionsPage
import com.erdemyesilcicek.flokiai.pages.ReadTalePage
import com.erdemyesilcicek.flokiai.pages.authpages.ForgotPasswordPage
import com.erdemyesilcicek.flokiai.pages.authpages.SignInPage
import com.erdemyesilcicek.flokiai.pages.authpages.SignUpPage
import com.erdemyesilcicek.flokiai.pages.optionspages.Feedback
import com.erdemyesilcicek.flokiai.pages.optionspages.PrivacyPolicy
import com.erdemyesilcicek.flokiai.pages.optionspages.TermsOfUse
import com.erdemyesilcicek.flokiai.pages.optionspages.UserInformation
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.HomeStackViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavController(
    categoryViewModel: CategoryViewModel,
    userInformationViewModel: UserInformationViewModel,
    loadingViewModel: LoadingViewModel,
    geminiViewModel: GeminiViewModel,
    db: FirebaseFirestore,
    auth: FirebaseAuth,
    homeStackViewModel: HomeStackViewModel
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null || !currentUser.isEmailVerified) {
            navController.navigate("GetStartedPage") {
                popUpTo("GetStartedPage") { inclusive = true }
            }
        } else {
            userInformationViewModel.loadUserInformation()
            navController.navigate("HomePage") {
                popUpTo("GetStartedPage") { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "GetStartedPage"
    ) {
        composable(route = "GetStartedPage") { GetStartedPage(navController) }

        composable(route = "SignUpPage") { SignUpPage(navController) }

        composable(route = "SignInPage") { SignInPage(navController, userInformationViewModel) }

        composable(route = "ForgotPasswordPage") { ForgotPasswordPage(navController) }

        composable(route = "HomePage") {
            HomePage(
                navController,
                categoryViewModel,
                loadingViewModel,
                homeStackViewModel
            )
        }

        composable(
            route = "ReadTalePage" + "?id={id}",
            arguments = listOf(navArgument(
                "id"
            ) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            val id = it.arguments?.getInt("id")!!
            ReadTalePage(
                navController,
                id,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(
            route = "AiReadTalePage" + "?taleId={taleId}",
            arguments = listOf(navArgument("taleId") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            val taleId = it.arguments?.getString("taleId")!!
            AiReadTalePage(
                navController,
                taleId,
                db,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(route = "CreateTalePage") {
            CreateTalePage(
                navController,
                categoryViewModel,
                userInformationViewModel,
                loadingViewModel
            )
        }

        composable(route = "LoadingPage") {
            LoadingPage(
                loadingViewModel,
                geminiViewModel,
                db,
                navController,
                categoryViewModel
            )
        }

        composable(route = "OptionsPage") {
            OptionsPage(
                navController,
                userInformationViewModel,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(route = "UserInformation") {
            UserInformation(
                navController,
                userInformationViewModel,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(route = "Feedback") {
            Feedback(
                navController,
                loadingViewModel,
                categoryViewModel,
            )
        }

        composable(route = "PrivacyPolicy") { PrivacyPolicy(navController, loadingViewModel, categoryViewModel) }

        composable(route = "TermsOfUse") { TermsOfUse(navController, loadingViewModel, categoryViewModel) }

        composable(route = "TaleCard") {
            TaleCard(
                navController = navController,
                tale = Tale(
                    5,
                    "",
                    "",
                    0,
                    ""
                )
            )
        }
    }
}