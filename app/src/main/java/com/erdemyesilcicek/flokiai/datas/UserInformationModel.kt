package com.erdemyesilcicek.flokiai.datas

data class UserInformationModel(
    var language : String = "" ?: "English",
    var yourName : String = "",
    var age : Int = 0,
    var gender : String = "",
    var dadName : String = "",
    var momName : String = "",
    var siblingName : String = "",
    var petName : String = "",
)