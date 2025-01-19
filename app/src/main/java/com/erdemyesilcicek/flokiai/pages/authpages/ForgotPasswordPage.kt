package com.erdemyesilcicek.flokiai.pages.authpages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomTextInput

@Composable
fun ForgotPasswordPage(navController: NavController) {
    // mail girilmesi için bir textfield oluşturulacak.
    // bir de altına reset password butonu. butona tıklayınca link gidiyor. gönderildiğinde alert ile bilgi verilsin.
    // alertteki butona tıklayınca geri dönülsün (sign in).
    // reset password.

    val email = remember { mutableStateOf("") }

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
    }
}