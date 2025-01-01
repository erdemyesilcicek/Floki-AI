package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PowerSettingsNew
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.CustomOptionsCard
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.OptionsCard
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.AuthViewModel
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun OptionsPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    userInformationViewModel: UserInformationViewModel,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel
) {
    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = true,
                isEnableBarButton = true,
                "",
                navController = navController,
                loadingViewModel,
                categoryViewModel,
                {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

            val optionsList = listOf<OptionsCard>(
                OptionsCard(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User Information",
                    itemText = stringResource(id = R.string.options_page_user_information),
                    summary = stringResource(id = R.string.options_page_user_information_summary),
                    onClick = { navController.navigate("UserInformation") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Feedback, Contact",
                    itemText = stringResource(id = R.string.options_page_contact),
                    summary = stringResource(id = R.string.options_page_contact_summary),
                    onClick = { navController.navigate("Feedback") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.PrivacyTip,
                    contentDescription = "Privacy Policy",
                    itemText = stringResource(id = R.string.options_page_privacy_policy),
                    summary = stringResource(id = R.string.options_page_privacy_policy_summary),
                    onClick = {
                        //privacy policy
                    }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.InsertDriveFile,
                    contentDescription = "Terms of Use",
                    itemText = stringResource(id = R.string.options_page_terms_of_use),
                    summary = stringResource(id = R.string.options_page_terms_of_use_summary),
                    onClick = {
                        //terms of use
                    }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.PowerSettingsNew,
                    contentDescription = "Log out",
                    itemText = stringResource(id = R.string.options_page_log_out),
                    summary = stringResource(id = R.string.options_page_log_out_summary),
                    onClick = {
                        alertDialogActive = true
                    }
                )
            )

            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(optionsList) { index, item ->
                    CustomOptionsCard(
                        navController = navController,
                        imageVector = optionsList[index].imageVector,
                        contentDescription = optionsList[index].contentDescription,
                        itemText = optionsList[index].itemText,
                        summary = optionsList[index].summary,
                        onClick = optionsList[index].onClick
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

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
            if (alertDialogActive) {
                CustomAlertDialog(
                    title = stringResource(id =R.string.options_page_exit_alert_title),
                    message = stringResource(id =R.string.options_page_exit_alert_message),
                    buttonText = stringResource(id =R.string.options_page_exit_alert_first_button),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    onButtonClick = {
                        userInformationViewModel.clearUserInformation()
                        authViewModel.logout()
                        alertDialogActive = false
                        navController.navigate("GetStartedPage") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onDismiss = { /*TODO*/ },
                    isDoubleButton = true,
                    secondButtonText = stringResource(id =R.string.options_page_exit_alert_second_button),
                    secondButtonColor = MaterialTheme.colorScheme.primary,
                    secondButtonOnClick = {
                        alertDialogActive = false
                    },
                )
            }
        }
    }
}