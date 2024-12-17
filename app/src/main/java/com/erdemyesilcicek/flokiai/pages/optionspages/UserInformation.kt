package com.erdemyesilcicek.flokiai.pages.optionspages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun UserInformation(
    navController: NavController
) {
    // State'leri tanımla
    val language = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val dadName = remember { mutableStateOf("") }
    val momName = remember { mutableStateOf("") }
    val sisName = remember { mutableStateOf("") }

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
                "Save",
                onClick = {
                    println("Save fab clicked")
                    println("Language: ${language.value}")
                    println("Name: ${name.value}")
                    println("Age: ${age.value}")
                    println("Gender: ${gender.value}")
                    println("Dad's Name: ${dadName.value}")
                    println("Mom's Name: ${momName.value}")
                    println("Sis's Name: ${sisName.value}")
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
                painter = painterResource(id = R.drawable.animalschicken),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "User Information",
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
                        title = "Language",
                        label = "Enter Language",
                        text = language.value, // State
                        onValueChange = { language.value = it }, // Değişim callback'i
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Name",
                        label = "Enter Name",
                        text = name.value,
                        onValueChange = { name.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Age",
                        label = "Enter Age",
                        text = age.value,
                        onValueChange = { age.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Number
                    )
                    CustomTextInput(
                        title = "Gender",
                        label = "Enter Gender",
                        text = gender.value,
                        onValueChange = { gender.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Dad's Name",
                        label = "Enter Dad's Name",
                        text = dadName.value,
                        onValueChange = { dadName.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Mom's Name",
                        label = "Enter Mom's Name",
                        text = momName.value,
                        onValueChange = { momName.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextInput(
                        title = "Sis's Name",
                        label = "Enter Sis's Name",
                        text = sisName.value,
                        onValueChange = { sisName.value = it },
                        isSingleLine = true,
                        isVisual = true,
                        keyboardType = KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.padding(40.dp))

                    Row(
                        modifier = Modifier.fillParentMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Created by Erdem",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = myFont,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}
