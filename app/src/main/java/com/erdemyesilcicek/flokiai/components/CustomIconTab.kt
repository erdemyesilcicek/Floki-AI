package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconTab(icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) Color(0xFFFFAAE8) else Color(0xFF7879A5),
        modifier = Modifier
            .size(30.dp)
            .clickable(
                indication = null, // Ripple efektini kaldırır
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    )
}