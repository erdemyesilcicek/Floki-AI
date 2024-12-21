package com.erdemyesilcicek.flokiai.datas

import com.google.firebase.firestore.PropertyName

data class TaleDetails(
    @PropertyName("genre") val genre: String = "",
    @PropertyName("genreImage") val genreImage: Int = 0,
    @PropertyName("season") val season: String = "",
    @PropertyName("animals") val animals: List<String> = listOf(),
    @PropertyName("characters") val characters: List<String> = listOf(),
    @PropertyName("family") val family: List<String> = listOf(),
    @PropertyName("userInformation") val userInformation: UserInformationModel = UserInformationModel()
)
