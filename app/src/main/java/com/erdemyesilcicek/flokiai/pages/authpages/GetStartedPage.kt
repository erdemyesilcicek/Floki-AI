package com.erdemyesilcicek.flokiai.pages.authpages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun GetStartedPage(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(animation = R.raw.lottiemagicbook)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id =R.string.app_name),
                fontSize = 38.sp,
                fontFamily = myFont,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id =R.string.get_started_page_description),
                fontSize = 15.sp,
                fontFamily = myFont,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomExtendedFAB(
                containerColor = MaterialTheme.colorScheme.primary,
                text = stringResource(id =R.string.get_started_page_button)
            ) {
                navController.navigate("SignUpPage")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(id =R.string.already_have_an_account),
                    fontSize = 12.sp,
                    fontFamily = myFont,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2
                )
                TextButton(
                    modifier = Modifier,
                    onClick = { navController.navigate("SignInPage") }) {
                    Text(
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                        text = stringResource(id =R.string.get_started_page_sign_in),
                        fontSize = 12.sp,
                        fontFamily = myFont,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}