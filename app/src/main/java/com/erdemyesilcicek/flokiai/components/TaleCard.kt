package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.datas.Tale
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun TaleCard(navController: NavController, card: Tale) {
    TaleCardTitle(card.title)

    OutlinedCard(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { navController.navigate("ReadTalePage" + "?id=${card.id}") }
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(card.image),
                contentDescription = "Tale Photo",
                contentScale = ContentScale.Fit
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    maxLines = 5,
                    text = card.content,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = myFont,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
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
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = myFont,
        modifier = Modifier
            .fillMaxWidth()
    )
}