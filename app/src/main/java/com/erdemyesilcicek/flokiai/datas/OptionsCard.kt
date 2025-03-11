package com.erdemyesilcicek.flokiai.datas

import androidx.compose.ui.graphics.painter.Painter

data class OptionsCard(
    val painter: Painter,
    val contentDescription: String,
    val itemText: String,
    val summary: String,
    val onClick:  () -> Unit
)