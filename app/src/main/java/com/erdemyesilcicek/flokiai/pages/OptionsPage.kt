package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.components.CustomOptionsCard
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.OptionsCard
import com.erdemyesilcicek.flokiai.utils.footer
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun OptionsPage(
    navController: NavController
) {
    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = true,
                "",
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val optionsList = listOf<OptionsCard>(
                OptionsCard(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User Information",
                    itemText = "User Information",
                    summary = "View and edit your personal information",
                    onClick = { navController.navigate("UserInformation") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Feedback, Contact",
                    itemText = "Contact",
                    summary = "Send feedback or contact us",
                    onClick = { navController.navigate("Feedback") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.PrivacyTip,
                    contentDescription = "Privacy Policy",
                    itemText = "Privacy Policy",
                    summary = "View our privacy policy",
                    onClick = { println("privacy policy") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.InsertDriveFile,
                    contentDescription = "Terms of Use",
                    itemText = "Terms of Use",
                    summary = "View our terms of use",
                    onClick = { println("term of use") }
                )
            )

            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(optionsList) { index, item ->
                    CustomOptionsCard(
                        navController = navController,
                        imageVector = optionsList[index].imageVector,
                        contentDescription = optionsList[index].contentDescription,
                        itemText = optionsList[index].itemText,
                        summary = optionsList[index].summary,
                        onClick = optionsList[index].onClick
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Created by Erdem",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = Color.LightGray
                )
            }
        }
    }
}