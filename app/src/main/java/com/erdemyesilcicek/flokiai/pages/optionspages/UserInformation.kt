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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
                    CustomTextInput("Language", "Enter Language", true)
                    CustomTextInput("Name", "Enter Name", true)
                    CustomTextInput("Age", "Enter Age", true)
                    CustomTextInput("Gender", "Enter Gender", true)
                    CustomTextInput("Dad's Name", "Enter Dad's Name", true)
                    CustomTextInput("Mom's Name", "Enter Mom's Name", true)
                    CustomTextInput("Sis's Name", "Enter Sis's Name", true)

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