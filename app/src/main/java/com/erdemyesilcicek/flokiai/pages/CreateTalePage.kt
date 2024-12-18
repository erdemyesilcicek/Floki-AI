package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
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
import com.erdemyesilcicek.flokiai.components.CustomCategorySection
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomText
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.CategoryCard
import com.erdemyesilcicek.flokiai.lists.animalList
import com.erdemyesilcicek.flokiai.lists.characterList
import com.erdemyesilcicek.flokiai.lists.familyList
import com.erdemyesilcicek.flokiai.lists.genreList
import com.erdemyesilcicek.flokiai.lists.seasonList
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel
import com.erdemyesilcicek.flokiai.viewmodels.UserInformationViewModel

@Composable
fun CreateTalePage(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
    userInformationViewModel: UserInformationViewModel,
    loadingViewModel: LoadingViewModel
) {
    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = false,
                "Create Tale",
                navController
            )
        },
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                "Create Tale",
                onClick = {
                    var genre: String = categoryViewModel.selectedGenre.get(0).text
                    var season: String = categoryViewModel.selectedSeason.get(0).text
                    val animalTexts: List<String> = categoryViewModel.selectedAnimals.map { it.text }
                    val characterTexts: List<String> = categoryViewModel.selectedCharacters.map { it.text }
                    val familyTexts: List<String> = categoryViewModel.selectedFamily.map { it.text }
                    val userInformation = userInformationViewModel.userInformation.value

                    loadingViewModel.updateLoadingData(
                        genre = genre,
                        season = season,
                        animals = animalTexts,
                        characters = characterTexts,
                        family = familyTexts,
                        userInformation = userInformation
                    )

                    navController.navigate("LoadingPage")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            items(1) {
                CustomText("Genre")
                CustomCategorySection(
                    list = genreList,
                    false,
                    selectedList = categoryViewModel.selectedGenre,
                    onSelectionChange = { category ->
                        categoryViewModel.selectGenre(category)
                })

                CustomText("Season")
                CustomCategorySection(
                    list = seasonList,
                    false,
                    selectedList = categoryViewModel.selectedSeason,
                    onSelectionChange = { category ->
                        categoryViewModel.selectSeason(category)
                    }
                )

                CustomText("Animal")
                CustomCategorySection(
                    list = animalList,
                    true,
                    selectedList = categoryViewModel.selectedAnimals,
                    onSelectionChange = { category ->
                        categoryViewModel.selectAnimal(category)
                    }
                )

                CustomText("Character")
                CustomCategorySection(
                    list = characterList,
                    true,
                    selectedList = categoryViewModel.selectedCharacters,
                    onSelectionChange = { category ->
                        categoryViewModel.selectCharacter(category)
                    }
                )

                CustomText("Include in the tale")
                CustomCategorySection(
                    list = familyList,
                    true,
                    selectedList = categoryViewModel.selectedFamily,
                    onSelectionChange = { category ->
                        categoryViewModel.selectFamily(category)
                    }
                )

                Spacer(modifier = Modifier.padding(50.dp))

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