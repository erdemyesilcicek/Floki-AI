package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.erdemyesilcicek.flokiai.datas.UserInformationModel

class LoadingViewModel : ViewModel() {
    var genre by mutableStateOf("")
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
        season: String,
        animals: List<String>,
        characters: List<String>,
        family: List<String>,
        userInformation: UserInformationModel?
    ) {
        this.genre = genre
        this.season = season
        this.animals = animals
        this.characters = characters
        this.family = family
        this.userInformation = userInformation
    }
}
