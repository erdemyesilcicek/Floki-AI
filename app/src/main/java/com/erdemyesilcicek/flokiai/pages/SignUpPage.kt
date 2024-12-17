package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextButton
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel

@Composable
fun SignUpPage(
    navController: NavController,
    authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroundwall),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Create Account",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Create an account to get started!",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .imePadding()
                    .verticalScroll(scrollState),
            ) {
                CustomTextInput(
                    title = "Email",
                    label = "Enter your email",
                    text = email.value, // State değeri
                    onValueChange = { email.value = it }, // Değişim callback'i
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Email
                )

                CustomTextInput(
                    title = "Password",
                    label = "Enter your password",
                    text = password.value,
                    onValueChange = { password.value = it },
                    isSingleLine = true,
                    isVisual = false,
                    keyboardType = KeyboardType.Password
                )

                CustomTextInput(
                    title = "Confirm Password",
                    label = "Confirm Password",
                    text = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    isSingleLine = true,
                    isVisual = false,
                    keyboardType = KeyboardType.Password
                )

                if (errorMessage.value.isNotEmpty()) {
                    Text(
                        text = errorMessage.value,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
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
                    text = "Sign Up"
                ) {
                    if (password.value == confirmPassword.value) {
                        errorMessage.value = ""
                        authViewModel.register(email.value, password.value)
                        navController.navigate("SignInPage")
                        println("auth is over.")
                    } else {
                        errorMessage.value = "Passwords do not match!"
                    }
                }
                CustomTextButton(
                    text = "Already have an account?",
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    navController.navigate("SignInPage")
                }
            }
        }
    }
}