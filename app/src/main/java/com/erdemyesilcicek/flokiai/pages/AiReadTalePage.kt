package com.erdemyesilcicek.flokiai.pages

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onSurfaceVariant

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
        containerColor = backgroundColor,
        floatingActionButton = {
            /*
            FloatingActionButton(
                onClick = { /* Dinleme işlevselliği eklenecek */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Outlined.VolumeUp,
                    contentDescription = stringResource(id = R.string.listen_tale),
                    tint = Color.White
                )
            }

             */
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .background(backgroundColor)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hikaye başlığı ve ikon kartı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = TaleTitle.value,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = myFont,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.hugeaicreated),
                            contentDescription = "magic",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Hikaye içeriği
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(24.dp),
                    fontWeight = FontWeight.Normal,
                    fontFamily = myFont,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    text = TaleItself.value,
                    color = textColor
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Alt bilgi
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.created_by_erdem),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = myFont,
                    color = Color.Gray
                )
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