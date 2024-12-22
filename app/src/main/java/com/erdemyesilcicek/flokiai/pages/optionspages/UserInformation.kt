package com.erdemyesilcicek.flokiai.pages.optionspages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.erdemyesilcicek.flokiai.components.CustomDropdownMenu
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun UserInformation(
    navController: NavController,
    userInformationViewModel: UserInformationViewModel
) {
    val userInfo by userInformationViewModel.userInformation.observeAsState(UserInformationModel())

    var language by remember { mutableStateOf(userInfo.language) }
    var name by remember { mutableStateOf(userInfo.yourName) }
    var age by remember { mutableStateOf(userInfo.age.toString()) }
    var gender by remember { mutableStateOf(userInfo.gender) }
    var dadName by remember { mutableStateOf(userInfo.dadName) }
    var momName by remember { mutableStateOf(userInfo.momName) }
    var siblingName by remember { mutableStateOf(userInfo.siblingName) }
    var petName by remember { mutableStateOf(userInfo.petName) }

    val options = listOf("English", "German", "Turkish")
    val scrollState = rememberScrollState()

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
                    val updatedUserInfo = UserInformationModel(
                        yourName = name,
                        age = age.toIntOrNull() ?: 0,
                        gender = gender,
                        dadName = dadName,
                        momName = momName,
                        siblingName = siblingName,
                        petName = petName,
                        language = language
                    )
                    userInformationViewModel.updateUserInformation(updatedUserInfo)
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
            Column(
                modifier = Modifier
                    .imePadding()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier.padding(start = 80.dp, end = 80.dp, top = 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.padding(10.dp),
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "app logo",
                            contentScale = ContentScale.Fit
                        )
                    }

                    Text(
                        text = "User Information",
                        fontFamily = FontFamily.Default,
                        fontSize = 18.sp
                    )
                }

                CustomDropdownMenu(
                    title = "Tale Language",
                    label = "Select Language",
                    options = options,
                    selectedOption = language,
                    onOptionSelected = { language = it }
                )
                CustomTextInput(
                    title = "Name",
                    label = "Enter Name",
                    text = name,
                    onValueChange = { name = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Age",
                    label = "Enter Age",
                    text = age,
                    onValueChange = { age = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Number,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Gender",
                    label = "Enter Gender",
                    text = gender,
                    onValueChange = { gender = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Dad Name",
                    label = "Enter Dad Name",
                    text = dadName,
                    onValueChange = { dadName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Mom Name",
                    label = "Enter Mom Name",
                    text = momName,
                    onValueChange = { momName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Sibling Name",
                    label = "Enter Sibling Name",
                    text = siblingName,
                    onValueChange = { siblingName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = "Pet Name",
                    label = "Enter Pet Name",
                    text = petName,
                    onValueChange = { petName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )

                Spacer(modifier = Modifier.padding(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
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