package com.erdemyesilcicek.flokiai.datas

data class TaleDetails(
    val genre: String?,
    val season: String?,
    val animals: List<String>?,
    val characters: List<String>?,
    val family: List<String>?,
    val userInformation: UserInformationModel?,
    val userId: String?
)
