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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DoDisturbAlt
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.datas.AiTale
import com.erdemyesilcicek.flokiai.utils.myFont
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AiTaleCard(
    navController: NavController,
    aiTale: AiTale,
    db: FirebaseFirestore
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 5.dp,
                bottom = 5.dp
            ).shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate("AiReadTalePage" + "?taleId=${aiTale.taleId}")
                }
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimaryContainer),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = aiTale.GenreImage),
                contentDescription = "Tale Photo",
                contentScale = ContentScale.Fit
            )
            Column(
                Modifier.fillMaxSize().padding(start = 12.dp, end = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AiTaleCardTitle(aiTale.TaleTitle)

                Text(
                    text = aiTale.TaleSummary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontFamily = myFont,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Row(
                    modifier = Modifier.fillMaxWidth().zIndex(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text ="5 min ", color = MaterialTheme.colorScheme.tertiary)
                    Icon(
                        painter = painterResource(id = R.drawable.hugeclock),
                        contentDescription = "Clock",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Composable
fun AiTaleCardTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontFamily = myFont,
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    )
}