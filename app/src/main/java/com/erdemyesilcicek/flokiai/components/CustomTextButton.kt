package com.erdemyesilcicek.flokiai.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun CustomTextButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontFamily = myFont,
            fontWeight = FontWeight.Normal,
            color = color
        )
    }
}