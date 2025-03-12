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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.lists.TaleList
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel

@Composable
fun ReadTalePage(
    navController: NavController,
    id: Int?,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel
) {
    val tale = TaleList.get(id!!)

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = true,
                isEnableBarButton = true,
                stringResource(id = R.string.my_tales),
                navController,
                loadingViewModel,
                categoryViewModel,
                {}
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
                text = tale.title,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                textAlign = TextAlign.Center
            )

            Icon(painter = painterResource(id =R.drawable.hugeaicreated), contentDescription = "magic")

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(1) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        fontWeight = FontWeight.Normal,
                        fontFamily = myFont,
                        fontSize = 20.sp,
                        text = tale.content
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
        }
    }
}