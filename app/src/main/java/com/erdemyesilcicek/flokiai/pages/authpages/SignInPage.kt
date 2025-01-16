package com.erdemyesilcicek.flokiai.pages.authpages

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextButton
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    userInformationViewModel: UserInformationViewModel
) {
    val currentUser = authViewModel.currentUser.value
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = authViewModel.errorMessage.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val localErrorMessage = remember { mutableStateOf("") }
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
                LottieAnimation(animation = R.raw.lottietap)
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
                    text = stringResource(id = R.string.sign_in_page_login_here),
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
                    text = stringResource(id = R.string.sign_in_page_description),
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
                    .padding(start = 10.dp, end = 10.dp)
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
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        coroutineScope.launch {
                            authViewModel.resendVerificationEmail(email.value)
                        }
                    }
                ) {
                    Text(text = "RESEND BUTTON")
                }
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
            }
            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 14.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextButton(
                    text = stringResource(id = R.string.sign_in_page_forgot_password),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    navController.navigate("ForgotPasswordPage")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (localErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = localErrorMessage.value,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                CustomExtendedFAB(
                    containerColor = MaterialTheme.colorScheme.primary,
                    text = stringResource(id = R.string.sign_in_page_button)
                ) {
                    if (email.value.isEmpty() || password.value.isEmpty()) {
                        authViewModel.updateErrorMessage("Please fill in all fields.")
                    } else {
                        localErrorMessage.value = ""
                        coroutineScope.launch {
                            if(currentUser != null) {
                                authViewModel.signIn(email.value, password.value)
                            } else {
                                // burada if ile kontrol edilmeli duruma göre hata mesajı verilmeli
                                println("CURRENT USER NULL GELİYOR.")
                            }
                        }
                    }
                }
                CustomTextButton(
                    text = stringResource(id = R.string.sign_in_page_create_account),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    localErrorMessage.value = ""
                    authViewModel.updateErrorMessage("")
                    navController.navigate("SignUpPage")
                }
            }
        }
    }
}