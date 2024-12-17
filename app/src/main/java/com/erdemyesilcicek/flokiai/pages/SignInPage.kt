package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignInPage(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    val errorMessage = remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

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
                    text = "Login here",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Welcome back you've been missed!",
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
                    .padding(start = 10.dp, end = 10.dp),
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
                    text = password.value, // State değeri
                    onValueChange = { password.value = it }, // Değişim callback'i
                    isSingleLine = true,
                    isVisual = false,
                    keyboardType = KeyboardType.Password
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextButton(
                    text = "Forgot your password?",
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
                // Hata Mesajı
                if (errorMessage.value.isNotEmpty()) {
                    Text(
                        text = errorMessage.value,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                CustomExtendedFAB(
                    containerColor = MaterialTheme.colorScheme.primary,
                    text = "Sign In"
                ) {
                    auth.signInWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                errorMessage.value = ""
                                authViewModel.login(email.value, password.value)
                                println("Login Successful!")
                                navController.navigate("HomePage")
                            } else {
                                errorMessage.value = "Email or password is incorrect, or the user is not registered."
                            }
                        }
                }
                CustomTextButton(
                    text = "Create an account",
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    navController.navigate("SignUpPage")
                }
            }
        }
    }


}