package com.erdemyesilcicek.flokiai.datas

import androidx.compose.ui.graphics.vector.ImageVector

data class OptionsCard(
    val imageVector: ImageVector,
    val contentDescription: String,
    val itemText: String,
    val onClick:  () -> Unit
)