package com.erdemyesilcicek.flokiai.pages.authpages

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextButton
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun SignUpPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    userInformationViewModel: UserInformationViewModel
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val localErrorMessage = remember { mutableStateOf("") }

    val loginState = authViewModel.loginState.value
    val errorMessage = authViewModel.errorMessage.value

    LaunchedEffect(loginState) {
        if (loginState == true) {
            userInformationViewModel.loadUserInformation()
            navController.navigate("HomePage") {
                popUpTo("SignUpPage") { inclusive = true }
            }
        }
    }

    val scrollState = rememberScrollState()

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

                if (localErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = localErrorMessage.value,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
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
                    if(email.value.isEmpty() || password.value.isEmpty() || confirmPassword.value.isEmpty()) {
                        localErrorMessage.value = "Please fill in all fields"
                    } else{
                        if (password.value == confirmPassword.value) {
                            localErrorMessage.value = ""
                            authViewModel.register(email = email.value, password = password.value)
                        } else {
                            localErrorMessage.value = "Passwords do not match!"
                        }
                    }
                }
                CustomTextButton(
                    text = stringResource(id = R.string.already_have_an_account),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    navController.navigate("SignInPage")
                }
            }
        }
    }
}