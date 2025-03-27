package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.erdemyesilcicek.flokiai.datas.UserInformationModel

class LoadingViewModel : ViewModel() {
    var genre by mutableStateOf("")
        private set
    var genreImage by mutableIntStateOf(0)
        private set
    var season by mutableStateOf("")
        private set
    var animals: List<String> by mutableStateOf(emptyList())
        private set
    var characters: List<String> by mutableStateOf(emptyList())
        private set
    var family: List<String> by mutableStateOf(emptyList())
        private set
    var userInformation: UserInformationModel? by mutableStateOf(null)
        private set

    fun updateLoadingData(
        genre: String,
        genreImage: Int,
        season: String,
        animals: List<String>,
        characters: List<String>,
        family: List<String>,
        userInformation: UserInformationModel?
    ) {
        this.apply {
            this.genre = genre
            this.genreImage = genreImage
            this.season = season
            this.animals = animals
            this.characters = characters
            this.family = family
            this.userInformation = userInformation
        }
    }

    fun clearLoadingData() {
        updateLoadingData(
            genre = "",
            genreImage = 0,
            season = "",
            animals = emptyList(),
            characters = emptyList(),
            family = emptyList(),
            userInformation = null
        )
    }
}
