package com.erdemyesilcicek.flokiai.pages.authpages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpPage(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LottieAnimation(animation = R.raw.lottierabbit)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = stringResource(id = R.string.sign_up_page_create_account),
                    textAlign = TextAlign.Start,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = stringResource(id = R.string.sign_up_page_description),
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
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

                CustomTextInput(
                    title = stringResource(id = R.string.title_password),
                    label = stringResource(id = R.string.label_password),
                    text = password.value,
                    onValueChange = { password.value = it },
                    isSingleLine = true,
                    isVisual = false,
                    keyboardType = KeyboardType.Password,
                    isBigCanvas = false
                )

                CustomTextInput(
                    title = stringResource(id = R.string.sign_up_page_title_confirm_password),
                    label = stringResource(id = R.string.sign_up_page_label_confirm_password),
                    text = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    isSingleLine = true,
                    isVisual = false,
                    keyboardType = KeyboardType.Password,
                    isBigCanvas = false
                )
            }
            if (message.value.isNotEmpty()) {
                Text(
                    message.value,
                    color = Color.Red,
                    fontSize = 16.sp,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomExtendedFAB(
                    containerColor = MaterialTheme.colorScheme.primary,
                    text = stringResource(id = R.string.sign_up_page_button)
                ) {
                    viewModel.signUp(
                        email.value,
                        password.value,
                        confirmPassword.value,
                        { isVerified ->
                            if (isVerified) {
                                message.value = ""
                                alertDialogActive = true
                            }
                        },
                        { error ->
                            message.value = error
                        })
                }

                CustomTextButton(
                    text = stringResource(id = R.string.already_have_an_account),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    navController.navigate("SignInPage")
                }
            }
            if (alertDialogActive) {
                CustomAlertDialog(
                    title = stringResource(id =R.string.sign_up_page_alert_title),
                    message = stringResource(id =R.string.sign_up_page_alert_description),
                    buttonText = stringResource(id =R.string.sign_up_page_alert_button),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    onButtonClick = {
                        alertDialogActive = false
                        navController.navigate("SignInPage")
                    },
                    onDismiss = {
                        alertDialogActive = false
                        navController.navigate("SignInPage")
                    })
            }
        }
    }
}