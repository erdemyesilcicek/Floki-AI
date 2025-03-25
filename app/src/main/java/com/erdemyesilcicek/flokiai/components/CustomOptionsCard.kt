package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun CustomOptionsCard(
    navController: NavController,
    painter: Painter,
    contentDescription: String,
    itemText: String,
    summary: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    cardHeight: Int = 130
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize(),
                    painter = painter,
                    contentDescription = contentDescription,
                    tint = iconTint
                )
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                CustomOptionsCardTitle(itemText)

                Text(
                    text = summary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontFamily = myFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp,
                    letterSpacing = 0.25.sp
                )
            }
        }
    }
}

@Composable
fun CustomOptionsCardTitle(itemText: String) {
    Text(
        text = itemText,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = myFont,
        letterSpacing = 0.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}