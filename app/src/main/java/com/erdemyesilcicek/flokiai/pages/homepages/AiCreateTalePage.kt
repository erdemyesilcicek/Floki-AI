package com.erdemyesilcicek.flokiai.pages.homepages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AiCreateTalePage() {
    Text(
        text = "AI Created",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}