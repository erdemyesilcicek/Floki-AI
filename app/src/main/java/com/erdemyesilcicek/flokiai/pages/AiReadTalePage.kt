package com.erdemyesilcicek.flokiai.pages

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomAlertDialog
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AiReadTalePage(
    navController: NavController,
    taleId: String,
    db: FirebaseFirestore,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel
) {
    var TaleItself = remember { mutableStateOf<String>("") }
    var TaleTitle = remember { mutableStateOf<String>("") }

    var deleteAlertDialogActive by remember { mutableStateOf<Boolean>(false) }

    fun onDelete(taleId: String) {
        db.collection("tales").document(taleId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    LaunchedEffect(Unit) {
        val docRef = db.collection("tales").document(taleId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
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
                isEnableBackButton = true,
                isEnableBarButton = true,
                stringResource(id = R.string.my_tales),
                navController,
                loadingViewModel,
                categoryViewModel,
                deleteOnClick = {
                    deleteAlertDialogActive = true
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        /*
        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                stringResource(id = R.string.listen_tale),
                onClick = {
                    println("Listen to Tale Button Clicked")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
         */
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

            Icon(painter = painterResource(id = R.drawable.hugeaicreated), contentDescription = "magic")

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
                            text = stringResource(id = R.string.created_by_erdem),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = myFont,
                            color = Color.LightGray
                        )
                    }
                }
            }
            if (deleteAlertDialogActive) {
                CustomAlertDialog(
                    title = stringResource(id = R.string.ai_created_tale_page_alert_title),
                    message = stringResource(id = R.string.ai_created_tale_page_alert_message),
                    buttonText = stringResource(id = R.string.ai_created_tale_page_alert_first_button),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    onButtonClick = {
                        onDelete(taleId)
                        deleteAlertDialogActive = false
                        navController.navigate("HomePage") {
                            popUpTo("HomePage") {
                                inclusive = true
                            }
                        }
                    },
                    onDismiss = { deleteAlertDialogActive = false },
                    isDoubleButton = true,
                    secondButtonText = stringResource(id = R.string.ai_created_tale_page_alert_second_button),
                    secondButtonColor = MaterialTheme.colorScheme.primary,
                    secondButtonOnClick = {
                        deleteAlertDialogActive = false
                    }
                )
            }
        }
    }
}