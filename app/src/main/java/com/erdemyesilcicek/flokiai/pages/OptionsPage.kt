package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.components.CustomOptionsCard
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.OptionsCard
import com.erdemyesilcicek.flokiai.pages.optionspages.DarkModeSheetContent
import com.erdemyesilcicek.flokiai.pages.optionspages.LanguageSheetContent
import com.erdemyesilcicek.flokiai.utils.footer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsPage(
    navController: NavController
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var currentSheet by remember { mutableStateOf<BottomSheetType?>(null) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShadowElevation = 8.dp,
        sheetTonalElevation = 8.dp,
        sheetContainerColor = Color.White,
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            when (currentSheet) {
                BottomSheetType.Language -> LanguageSheetContent { selectedLanguage ->
                    println("Selected Language: $selectedLanguage")
                    scope.launch { scaffoldState.bottomSheetState.hide() }
                }
                BottomSheetType.DarkMode -> DarkModeSheetContent { selectedOption ->
                    println("Dark Mode: $selectedOption")
                    scope.launch { scaffoldState.bottomSheetState.hide() }
                }
                null -> {}
            }
        },
    ) { innerPadding ->
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
                    .padding(innerPadding)
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
                        onClick = { navController.navigate("UserInformation") }
                    ),
                    OptionsCard(
                        imageVector = Icons.Outlined.Translate,
                        contentDescription = "Language",
                        itemText = "Language",
                        onClick = {
                            currentSheet = BottomSheetType.Language
                            scope.launch { scaffoldState.bottomSheetState.expand() }
                        }
                    ),
                    OptionsCard(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Feedback, Contact",
                        itemText = "Contact",
                        onClick = { navController.navigate("Feedback") }
                    ),
                    OptionsCard(
                        imageVector = Icons.Outlined.DarkMode,
                        contentDescription = "dark mode",
                        itemText = "Dark Mode",
                        onClick = {
                            currentSheet = BottomSheetType.DarkMode
                            scope.launch { scaffoldState.bottomSheetState.expand() }
                        }
                    ),
                    OptionsCard(
                        imageVector = Icons.Outlined.PrivacyTip,
                        contentDescription = "Privacy Policy",
                        itemText = "Privacy Policy",
                        onClick = { println("privacy policy") }
                    ),
                    OptionsCard(
                        imageVector = Icons.Outlined.InsertDriveFile,
                        contentDescription = "Terms of Use",
                        itemText = "Terms of Use",
                        onClick = { println("term of use") }
                    )
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .background(Color.White),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(optionsList.size) { index ->
                        CustomOptionsCard(
                            imageVector = optionsList[index].imageVector,
                            contentDescription = optionsList[index].contentDescription,
                            itemText = optionsList[index].itemText,
                            onClick = optionsList[index].onClick
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                footer()
            }
        }
    }
}

// Enum to manage BottomSheet types
enum class BottomSheetType {
    Language, DarkMode
}