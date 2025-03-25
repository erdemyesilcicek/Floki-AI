package com.erdemyesilcicek.flokiai.pages.optionspages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomTextInput
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import kotlinx.coroutines.delay

@Composable
fun Feedback(
    navController: NavController,
    loadingViewModel: LoadingViewModel,
    categoryViewModel: CategoryViewModel
) {
    val email = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    var alertDialogActive by remember { mutableStateOf<Boolean>(false) }
    val context = LocalContext.current

    // Animation states
    val headerVisibleState = remember { MutableTransitionState(false) }
    val formVisibleState = remember { MutableTransitionState(false) }
    
    // Start animations with slight delay between them
    LaunchedEffect(Unit) {
        headerVisibleState.targetState = true
        delay(300)
        formVisibleState.targetState = true
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
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header and logo section with animation
                AnimatedVisibility(
                    visibleState = headerVisibleState,
                    enter = fadeIn(animationSpec = tween(1000)) + 
                            slideInVertically(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                initialOffsetY = { -200 }
                            ),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.height(80.dp),
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "app logo",
                                contentScale = ContentScale.Fit
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.feedback_page_description),
                            fontFamily = myFont,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
                
                // Form section with animation
                AnimatedVisibility(
                    visibleState = formVisibleState,
                    enter = fadeIn(animationSpec = tween(1000)) + 
                            slideInVertically(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                initialOffsetY = { 300 }
                            ),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.feedback_page_title_message),
                                fontFamily = myFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                textAlign = TextAlign.Center
                            )
                            
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
                            
                            CustomTextInput(
                                title = stringResource(id = R.string.feedback_page_title_message),
                                label = stringResource(id = R.string.feedback_page_label_message),
                                text = message.value,
                                onValueChange = { message.value = it },
                                isSingleLine = false,
                                isVisual = true,
                                keyboardType = KeyboardType.Text,
                                isBigCanvas = true
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            CustomExtendedFAB(
                                MaterialTheme.colorScheme.primary,
                                stringResource(id = R.string.feedback_page_button),
                                onClick = {
                                    alertDialogActive = true
                                    println(message.value)
                                },
                            )
                        }
                    }
                }
            }
        }
        if(alertDialogActive){
            // Add animation to dialog
            AnimatedVisibility(
                visible = alertDialogActive,
                enter = fadeIn(animationSpec = tween(300)) + 
                        slideInVertically(
                            initialOffsetY = { 100 },
                            animationSpec = tween(300)
                        ),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                CustomAlertDialog(
                    title = stringResource(id = R.string.feedback_page_alert_title),
                    message = stringResource(id = R.string.feedback_page_alert_text),
                    buttonText = stringResource(id = R.string.feedback_page_alert_button),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    onButtonClick = {
                        alertDialogActive = false
                        navController.navigate("HomePage")
                    },
                    onDismiss = {
                        alertDialogActive = false
                        navController.navigate("HomePage")
                    }
                )
            }
        }
    }
}