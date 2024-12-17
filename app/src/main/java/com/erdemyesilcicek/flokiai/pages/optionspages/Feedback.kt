package com.erdemyesilcicek.flokiai.pages.optionspages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.components.HeaderBar

@Composable
fun Feedback(
    navController: NavController
) {
    val email = remember { mutableStateOf("") }
    val subject = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = true,
                "",
                navController
            )
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                "Send",
                onClick = {
                    println("Send fab clicked")
                })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.animalsbear),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "How can we help you?",
                fontFamily = FontFamily.Default,
                fontSize = 22.sp
            )
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(1) {
                    CustomTextInput(
                        title = "Email",
                        label = "Email",
                        text = email.value,
                        onValueChange = { email.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Email
                    )
                    CustomTextInput(
                        title = "Subject",
                        label = "Enter Subject",
                        text = subject.value,
                        onValueChange = { subject.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Message",
                        label = "Enter Message",
                        text = message.value,
                        onValueChange = { message.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                }
            }
        }
    }
}