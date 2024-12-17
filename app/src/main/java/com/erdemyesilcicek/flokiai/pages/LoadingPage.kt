package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun LoadingPage() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LottieAnimation(animation = R.raw.bigstick)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "PLEASE WAIT!",
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "We are loading your data...",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}