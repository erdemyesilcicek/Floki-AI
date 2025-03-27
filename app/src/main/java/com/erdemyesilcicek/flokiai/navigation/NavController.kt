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
import com.erdemyesilcicek.flokiai.pages.optionspages.UserInformation
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.HomeStackViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object AppRoutes {
    // Auth routes
    const val GET_STARTED = "GetStartedPage"
    const val SIGN_UP = "SignUpPage"
    const val SIGN_IN = "SignInPage"
    const val FORGOT_PASSWORD = "ForgotPasswordPage"

    // Main routes
    const val HOME = "HomePage"
    const val READ_TALE = "ReadTalePage"
    const val AI_READ_TALE = "AiReadTalePage"
    const val CREATE_TALE = "CreateTalePage"
    const val LOADING = "LoadingPage"

    // Options routes
    const val OPTIONS = "OptionsPage"
    const val USER_INFO = "UserInformation"
    const val FEEDBACK = "Feedback"

    // Components
    const val TALE_CARD = "TaleCard"

    // Route parameters
    const val READ_TALE_WITH_ID = "$READ_TALE?id={id}"
    const val AI_READ_TALE_WITH_ID = "$AI_READ_TALE?taleId={taleId}"
}

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
        val currentUser = auth.currentUser

        if (currentUser == null || !currentUser.isEmailVerified) {
            navController.navigate(AppRoutes.GET_STARTED) {
                popUpTo(AppRoutes.GET_STARTED) { inclusive = true }
            }
        } else {
            userInformationViewModel.loadUserInformation()
            navController.navigate(AppRoutes.HOME) {
                popUpTo(AppRoutes.GET_STARTED) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.GET_STARTED
    ) {
        // Auth screens
        composable(route = AppRoutes.GET_STARTED) {
            GetStartedPage(navController)
        }

        composable(route = AppRoutes.SIGN_UP) {
            SignUpPage(navController)
        }

        composable(route = AppRoutes.SIGN_IN) {
            SignInPage(navController, userInformationViewModel)
        }

        composable(route = AppRoutes.FORGOT_PASSWORD) {
            ForgotPasswordPage(navController)
        }

        // Main screens
        composable(route = AppRoutes.HOME) {
            HomePage(
                navController,
                categoryViewModel,
                loadingViewModel,
                homeStackViewModel
            )
        }

        composable(
            route = AppRoutes.READ_TALE_WITH_ID,
            arguments = listOf(navArgument("id") {
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
            route = AppRoutes.AI_READ_TALE_WITH_ID,
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

        composable(route = AppRoutes.CREATE_TALE) {
            CreateTalePage(
                navController,
                categoryViewModel,
                userInformationViewModel,
                loadingViewModel
            )
        }

        composable(route = AppRoutes.LOADING) {
            LoadingPage(
                loadingViewModel,
                geminiViewModel,
                db,
                navController,
                categoryViewModel
            )
        }

        // Options screens
        composable(route = AppRoutes.OPTIONS) {
            OptionsPage(
                navController,
                userInformationViewModel,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(route = AppRoutes.USER_INFO) {
            UserInformation(
                navController,
                userInformationViewModel,
                categoryViewModel,
                loadingViewModel
            )
        }

        composable(route = AppRoutes.FEEDBACK) {
            Feedback(
                navController,
                loadingViewModel,
                categoryViewModel,
            )
        }

        // Components
        composable(route = AppRoutes.TALE_CARD) {
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