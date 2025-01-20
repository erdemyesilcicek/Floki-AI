package com.erdemyesilcicek.flokiai.pages.authpages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.auth.AuthViewModel
import com.erdemyesilcicek.flokiai.auth.AuthViewModelFactory
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextButton
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun ForgotPasswordPage(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val email = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(scrollState)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LottieAnimation(animation = R.raw.security)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Reset your password",
                lineHeight = 42.sp,
                textAlign = TextAlign.Start,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Please enter your email, reset link will be sent to you.",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextInput(
                title = stringResource(id = R.string.title_email),
                label = stringResource(id = R.string.label_email),
                text = email.value,
                onValueChange = { email.value = it },
                isSingleLine = true,
                isVisual = true,
                keyboardType = KeyboardType.Email,
                isBigCanvas = false
            )
            if (message.value.isNotEmpty()) {
                Text(
                    message.value,
                    color = Color.Red,
                    fontSize = 16.sp,
                )
            }

            CustomExtendedFAB(
                containerColor = MaterialTheme.colorScheme.primary,
                text = "Reset Password",
            ) {
                viewModel.resetPassword(
                    email.value,
                    onSuccess = {
                        alertDialogActive = true
                    },
                    onError = {
                        message.value = it
                    }
                )
            }
            CustomTextButton(
                text = stringResource(id = R.string.already_have_an_account),
                color = MaterialTheme.colorScheme.onBackground
            ) {
                navController.navigate("SignInPage")
            }
        }
    }
    if (alertDialogActive) {
        CustomAlertDialog(
            title = "Password Reset",
            message = "Reset link sent to your email.",
            buttonText = "OK",
            buttonColor = MaterialTheme.colorScheme.primary,
            onButtonClick = {
                alertDialogActive = false
                navController.popBackStack()
            },
            onDismiss = {
                alertDialogActive = false
                navController.popBackStack()
            }
        )
    }
}