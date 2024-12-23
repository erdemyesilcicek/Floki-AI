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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.lists.TaleList
import com.erdemyesilcicek.flokiai.utils.myFont
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AiReadTalePage(navController: NavController, taleId: String, db: FirebaseFirestore) {
    var TaleItself = remember { mutableStateOf<String>("") }
    var TaleTitle = remember { mutableStateOf<String>("") }

    LaunchedEffect(Unit) {
        println("AiReadTalePage: $taleId")
        val docRef = db.collection("tales").document(taleId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    println("DocumentSnapshot data: ${document.data}")
                    TaleItself.value = document.data?.get("TaleItself").toString()
                    TaleTitle.value = document.data?.get("TaleTitle").toString()
                } else {
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("get failed with: $exception")
            }
    }

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = true,
                "My Tales",
                navController
            )
        },
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                "Listen Tale",
                onClick = {
                    println("Listen to Tale Button Clicked")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .background(Color.White),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = TaleTitle.value,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                textAlign = TextAlign.Center
            )

            Icon(imageVector = Icons.Outlined.AutoFixHigh, contentDescription = "magic")

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(1) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        fontWeight = FontWeight.Normal,
                        fontFamily = myFont,
                        fontSize = 20.sp,
                        text = TaleItself.value
                    )
                    Spacer(modifier = Modifier.padding(40.dp))

                    Row(
                        modifier = Modifier.fillParentMaxWidth(),
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
    }
}