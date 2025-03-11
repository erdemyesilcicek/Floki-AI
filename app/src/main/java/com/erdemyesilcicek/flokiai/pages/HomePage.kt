package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomIconTab
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.components.TaleCard
import com.erdemyesilcicek.flokiai.lists.TaleList
import com.erdemyesilcicek.flokiai.pages.homepages.AiCreatedTalePage
import com.erdemyesilcicek.flokiai.pages.homepages.DefaultTalePage
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.HomeStackViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel

@Composable
fun HomePage(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
    loadingViewModel: LoadingViewModel,
    homeStackViewModel: HomeStackViewModel
) {
    var selectedTab = homeStackViewModel.stack.intValue

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = false,
                if (selectedTab == 1) {
                    stringResource(id = R.string.home_page_world_of_tales)
                } else {
                    stringResource(id = R.string.home_page_created_tales)
                },
                navController,
                loadingViewModel,
                categoryViewModel,
                {}
            )
        },
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                stringResource(id = R.string.home_page_button),
                onClick = {
                    navController.navigate("CreateTalePage")
                })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomIconTab(
                    icon = painterResource(id = R.drawable.hugeaicreated),
                    isSelected = selectedTab == 0,
                    onClick = { homeStackViewModel.changeStack() }
                )

                Text(text = "|", fontSize = 20.sp)

                CustomIconTab(
                    icon = painterResource(id = R.drawable.hugebook),
                    isSelected = selectedTab == 1,
                    onClick = { homeStackViewModel.changeStack() }
                )
            }
            when (selectedTab) {
                0 -> AiCreatedTalePage(navController)
                1 -> DefaultTalePage(navController)
            }
        }
    }
}