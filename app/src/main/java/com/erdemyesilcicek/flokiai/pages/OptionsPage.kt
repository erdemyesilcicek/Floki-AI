package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.PeopleAlt
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsPage(
    navController: NavController
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf<@Composable () -> Unit>({}) }

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
                sheetContent = {
                    LanguageSheetContent { selectedLanguage ->
                        println("Selected Language: $selectedLanguage")
                        scope.launch { scaffoldState.bottomSheetState.hide() }
                    }
                }
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
                sheetContent = {
                    DarkModeSheetContent { selectedOption ->
                        println("Dark Mode: $selectedOption")
                        scope.launch { scaffoldState.bottomSheetState.hide() }
                    }
                }
                scope.launch { scaffoldState.bottomSheetState.expand() }
            }
        ),
        OptionsCard(
            imageVector = Icons.Outlined.PrivacyTip,
            contentDescription = "Privacy Policy",
            itemText = "Privacy Policy",
            onClick = { navController.navigate("PrivacyPolicy") }
        ),
        OptionsCard(
            imageVector = Icons.Outlined.InsertDriveFile,
            contentDescription = "Terms of Use",
            itemText = "Terms of Use",
            onClick = { navController.navigate("TermOfUse") }
        )
    )

    BottomSheetScaffold(
        modifier = Modifier.background(Color.White),
        sheetShadowElevation = 10.dp,
        sheetTonalElevation = 10.dp,
        sheetContainerColor = Color.White,
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding()
                    .background(Color.White)
            ) {
                sheetContent()
            }
        },
        sheetPeekHeight = 0.dp,
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(5.dp).background(Color.White),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Top
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
            }
        }
    }
}