package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    isEnableBackButton: Boolean,
    isEnableBarButton: Boolean,
    barText: String,
    navController: NavController,
    loadingViewModel: LoadingViewModel,
    categoryViewModel: CategoryViewModel,
    deleteOnClick: () -> Unit
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
            if (isEnableBarButton) {
                Box(
                ) {
                    Button(
                        onClick = {
                            loadingViewModel.clearLoadingData()
                            categoryViewModel.clearAllSelections()
                            navController.navigate("CreateTalePage")
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    ) {
                        Text(
                            color = Color.White,
                            text = stringResource(id = R.string.create_tale_button),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = myFont
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                ) {
                    Text(
                        text = barText,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 23.sp
                    )
                }
            }
        },
        navigationIcon = {
            if (isEnableBackButton) {
                IconButton(
                    onClick = {
                        if (
                            currentRoute == "UserInformation" ||
                            currentRoute == "Feedback" ||
                            currentRoute == "PrivacyPolicy" ||
                            currentRoute == "TermsOfUse"
                        ) {
                            navController.popBackStack()
                        } else {
                            loadingViewModel.clearLoadingData()
                            categoryViewModel.clearAllSelections()
                            navController.navigate("HomePage") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = "new back button",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            println(currentRoute)
            if(currentRoute == "AiReadTalePage?taleId={taleId}"){
                IconButton(
                    onClick = {
                        deleteOnClick()
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp),
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Menu Button",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else{
                IconButton(
                    onClick = {
                        if (currentRoute != "OptionsPage") {
                            navController.navigate("OptionsPage")
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp),
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu Button",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    )
}