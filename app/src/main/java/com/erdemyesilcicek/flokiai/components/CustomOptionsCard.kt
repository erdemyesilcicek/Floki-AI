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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    onClick: () -> Unit
) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 5.dp,
                bottom = 5.dp
            ),
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxSize()
                .weight(1f)
                .background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painter,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .weight(0.8f)
                    .padding(start = 12.dp, end = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                CustomOptionsCardTitle(itemText)

                Text(
                    modifier = Modifier.padding(10.dp),
                    text = summary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontFamily = myFont,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Composable
fun CustomOptionsCardTitle(itemText: String) {
    Text(
        text = itemText,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = myFont,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    )
}