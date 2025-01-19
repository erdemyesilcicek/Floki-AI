package com.erdemyesilcicek.flokiai.pages.authpages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.auth.AuthViewModel
import com.erdemyesilcicek.flokiai.auth.AuthViewModelFactory
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.CustomTextInput

@Composable
fun ForgotPasswordPage(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val email = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
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
        Button(
            onClick = {
                viewModel.resetPassword(
                    email.value,
                    onSuccess = {
                        alertDialogActive = true
                    },
                    onError = {
                        message.value = it
                    }
                )
            }) {
            Text(
                text = "RESET PASSWORD",
                fontSize = 16.sp
            )
        }
    }
    if (alertDialogActive) {
        CustomAlertDialog(
            title = "Password Reset",
            message = "Password reset link has been sent to your email address.",
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