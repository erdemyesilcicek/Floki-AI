package com.erdemyesilcicek.flokiai.pages

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.auth.AuthViewModel
import com.erdemyesilcicek.flokiai.auth.AuthViewModelFactory
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.CustomOptionsCard
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.OptionsCard
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun OptionsPage(
    navController: NavController,
    userInformationViewModel: UserInformationViewModel,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory()),
) {
    val context = LocalContext.current

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
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var alertDialogActive by remember { mutableStateOf<Boolean>(false) }

            val optionsList = listOf<OptionsCard>(
                OptionsCard(
                    painter = painterResource(id = R.drawable.hugeuser),
                    contentDescription = "User Information",
                    itemText = stringResource(id = R.string.options_page_user_information),
                    summary = stringResource(id = R.string.options_page_user_information_summary),
                    onClick = { navController.navigate("UserInformation") }
                ),
                OptionsCard(
                    painter = painterResource(id = R.drawable.hugecontact),
                    contentDescription = "Feedback, Contact",
                    itemText = stringResource(id = R.string.options_page_contact),
                    summary = stringResource(id = R.string.options_page_contact_summary),
                    onClick = { navController.navigate("Feedback") }
                ),
                OptionsCard(
                    painter = painterResource(id = R.drawable.hugepp),
                    contentDescription = "Privacy Policy",
                    itemText = stringResource(id = R.string.options_page_privacy_policy),
                    summary = stringResource(id = R.string.options_page_privacy_policy_summary),
                    onClick = {
                        val url = "https://floki-ai-web.vercel.app/privacy-policy"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                ),
                OptionsCard(
                    painter = painterResource(id = R.drawable.hugetof),
                    contentDescription = "Terms of Use",
                    itemText = stringResource(id = R.string.options_page_terms_of_use),
                    summary = stringResource(id = R.string.options_page_terms_of_use_summary),
                    onClick = {
                        val url = "https://floki-ai-web.vercel.app/term-of-use"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                ),
                OptionsCard(
                    painter = painterResource(id = R.drawable.hugelogout),
                    contentDescription = "Log out",
                    itemText = stringResource(id = R.string.options_page_log_out),
                    summary = stringResource(id = R.string.options_page_log_out_summary),
                    onClick = {
                        alertDialogActive = true
                    }
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(optionsList) { index, item ->
                    CustomOptionsCard(
                        navController = navController,
                        painter = optionsList[index].painter,
                        contentDescription = optionsList[index].contentDescription,
                        itemText = optionsList[index].itemText,
                        summary = optionsList[index].summary,
                        onClick = optionsList[index].onClick
                    )
                }
            }
            if (alertDialogActive) {
                CustomAlertDialog(
                    title = stringResource(id = R.string.options_page_exit_alert_title),
                    message = stringResource(id = R.string.options_page_exit_alert_message),
                    buttonText = stringResource(id = R.string.options_page_exit_alert_first_button),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    onButtonClick = {
                        viewModel.signOut(
                            onSuccess = {
                                userInformationViewModel.clearUserInformation()
                                alertDialogActive = false
                                navController.navigate("GetStartedPage") {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            onError = {
                                alertDialogActive = false
                            }
                        )
                    },
                    onDismiss = { alertDialogActive = false },
                    isDoubleButton = true,
                    secondButtonText = stringResource(id = R.string.options_page_exit_alert_second_button),
                    secondButtonColor = MaterialTheme.colorScheme.primary,
                    secondButtonOnClick = {
                        alertDialogActive = false
                    },
                )
            }
        }
    }
}