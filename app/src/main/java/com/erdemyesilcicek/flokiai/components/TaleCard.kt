package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.datas.Tale
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun TaleCard(navController: NavController, tale: Tale) {
    TaleCardTitle(tale.title)

    OutlinedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .clickable { navController.navigate("ReadTalePage" + "?id=${tale.id}") }
                .fillMaxSize()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(tale.image),
                contentDescription = "Tale Photo",
                contentScale = ContentScale.Fit
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = tale.summary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
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
fun TaleCardTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = myFont,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    )
}