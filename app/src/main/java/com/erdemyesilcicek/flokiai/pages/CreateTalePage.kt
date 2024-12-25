package com.erdemyesilcicek.flokiai.pages

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.components.CustomCategorySection
import com.erdemyesilcicek.flokiai.components.CustomExtendedFAB
import com.erdemyesilcicek.flokiai.components.CustomText
import com.erdemyesilcicek.flokiai.components.HeaderBar
import com.erdemyesilcicek.flokiai.datas.CategoryCard
import com.erdemyesilcicek.flokiai.lists.getAnimalList
import com.erdemyesilcicek.flokiai.lists.getCharacterList
import com.erdemyesilcicek.flokiai.lists.getFamilyList
import com.erdemyesilcicek.flokiai.lists.getGenreList
import com.erdemyesilcicek.flokiai.lists.getSeasonList
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            HeaderBar(
                isEnableBackButton = false,
                isEnableBarButton = false,
                stringResource(id = R.string.create_tale_button),
                navController
            )
        },
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            CustomExtendedFAB(
                MaterialTheme.colorScheme.primary,
                stringResource(id = R.string.create_tale_button),
                onClick = {
                    val genre = categoryViewModel.selectedGenre.getOrNull(0)?.text
                    val genreImage = categoryViewModel.selectedGenre.getOrNull(0)?.image
                    val season = categoryViewModel.selectedSeason.getOrNull(0)?.text
                    val animalTexts = categoryViewModel.selectedAnimals.map { it.text }
                    val characterTexts = categoryViewModel.selectedCharacters.map { it.text }
                    val familyTexts = categoryViewModel.selectedFamily.map { it.text }
                    val userInformation = userInformationViewModel.userInformation.value

                    when {
                        genre.isNullOrEmpty() -> {
                            Toast.makeText(context, "Please select a genre", Toast.LENGTH_SHORT)
                                .show()
                        }

                        season.isNullOrEmpty() -> {
                            Toast.makeText(context, "Please select a season", Toast.LENGTH_SHORT)
                                .show()
                        }

                        animalTexts.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Please select at least one animal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        characterTexts.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Please select at least one character",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        familyTexts.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Please select at least one family member",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            loadingViewModel.updateLoadingData(
                                genre = genre,
                                genreImage = genreImage!!,
                                season = season,
                                animals = animalTexts,
                                characters = characterTexts,
                                family = familyTexts,
                                userInformation = userInformation
                            )
                            navController.navigate("LoadingPage")
                        }
                    }
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
                CustomText(stringResource(id = R.string.genre_label))
                CustomCategorySection(
                    list = getGenreList(),
                    false,
                    selectedList = categoryViewModel.selectedGenre,
                    onSelectionChange = { category ->
                        categoryViewModel.selectGenre(category)
                    })

                CustomText(stringResource(id = R.string.season_label))
                CustomCategorySection(
                    list = getSeasonList(),
                    false,
                    selectedList = categoryViewModel.selectedSeason,
                    onSelectionChange = { category ->
                        categoryViewModel.selectSeason(category)
                    }
                )

                CustomText(stringResource(id = R.string.animal_label))
                CustomCategorySection(
                    list = getAnimalList(),
                    true,
                    selectedList = categoryViewModel.selectedAnimals,
                    onSelectionChange = { category ->
                        categoryViewModel.selectAnimal(category)
                    }
                )

                CustomText(stringResource(id = R.string.character_label))
                CustomCategorySection(
                    list = getCharacterList(),
                    true,
                    selectedList = categoryViewModel.selectedCharacters,
                    onSelectionChange = { category ->
                        categoryViewModel.selectCharacter(category)
                    }
                )

                CustomText(stringResource(id = R.string.family_label))
                CustomCategorySection(
                    list = getFamilyList(),
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