package com.erdemyesilcicek.flokiai.pages.optionspages

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.CustomDropdownMenu
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun UserInformation(
    navController: NavController,
    userInformationViewModel: UserInformationViewModel,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel
) {
    val userInfo by userInformationViewModel.userInformation.observeAsState(null)
    val error by userInformationViewModel.error.observeAsState(null)

    var language by remember { mutableStateOf(userInfo?.language ?: "") }
    var name by remember { mutableStateOf(userInfo?.yourName ?: "") }
    var age by remember { mutableStateOf(userInfo?.age?.toString() ?: "") }
    var gender by remember { mutableStateOf(userInfo?.gender ?: "") }
    var dadName by remember { mutableStateOf(userInfo?.dadName ?: "") }
    var momName by remember { mutableStateOf(userInfo?.momName ?: "") }
    var siblingName by remember { mutableStateOf(userInfo?.siblingName ?: "") }
    var petName by remember { mutableStateOf(userInfo?.petName ?: "") }

    //arkaplanda prompt ingilizce olduğu için ingilizce dışında dil ve cinsiyet seçeneği yok.
    val optionsLanguage = listOf("English", "German", "Turkish")
    val optionsGender = listOf("None", "Male", "Female")

    val scrollState = rememberScrollState()

    var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

    if (alertDialogActive == true) {
        CustomAlertDialog(
            title = stringResource(id = R.string.user_information_page_alert_title),
            message = stringResource(id = R.string.user_information_page_alert_text),
            buttonColor = MaterialTheme.colorScheme.primary,
            buttonText = stringResource(id = R.string.user_information_page_alert_button),
            onButtonClick = {
                alertDialogActive = false
                navController.navigate("HomePage")
            },
            onDismiss = { },
            isDoubleButton = false,
            secondButtonText = "",
            secondButtonColor = Color.Transparent,
            secondButtonOnClick = { }
        )
    }

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = true,
                isEnableBarButton = true,
                "",
                navController,
                loadingViewModel,
                categoryViewModel,
                {}
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                stringResource(id = R.string.user_information_page_button),
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
                    userInformationViewModel
                        .updateUserInformation(updatedUserInfo).let {
                            alertDialogActive = true
                        }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
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
                        text = stringResource(id = R.string.user_information_page_user_information),
                        fontFamily = FontFamily.Default,
                        fontSize = 18.sp
                    )
                }

                CustomDropdownMenu(
                    title = stringResource(id = R.string.user_information_page_title_tale_language),
                    label = stringResource(id = R.string.user_information_page_label_select_language),
                    options = optionsLanguage,
                    selectedOption = language,
                    onOptionSelected = { language = it }
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_name),
                    label = stringResource(id = R.string.user_information_page_label_enter_name),
                    text = name,
                    onValueChange = { name = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_age),
                    label = stringResource(id = R.string.user_information_page_label_enter_age),
                    text = age,
                    onValueChange = { age = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Number,
                    isBigCanvas = false
                )
                CustomDropdownMenu(
                    title = stringResource(id = R.string.user_information_page_title_gender),
                    label = stringResource(id = R.string.user_information_page_label_select_gender),
                    options = optionsGender,
                    selectedOption = gender,
                    onOptionSelected = { gender = it }
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_dad_name),
                    label = stringResource(id = R.string.user_information_page_label_enter_dad_name),
                    text = dadName,
                    onValueChange = { dadName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_mom_name),
                    label = stringResource(id = R.string.user_information_page_label_enter_mom_name),
                    text = momName,
                    onValueChange = { momName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_sibling_name),
                    label = stringResource(id = R.string.user_information_page_label_enter_sibling_name),
                    text = siblingName,
                    onValueChange = { siblingName = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text,
                    isBigCanvas = false
                )
                CustomTextInput(
                    title = stringResource(id = R.string.user_information_page_title_pet_name),
                    label = stringResource(id = R.string.user_information_page_label_enter_pet_name),
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
                        text = stringResource(id = R.string.created_by_erdem),
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