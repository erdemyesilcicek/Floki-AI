package com.erdemyesilcicek.flokiai.datas

import com.google.firebase.firestore.PropertyName

data class AiTale(
    @PropertyName("GenreImage") val GenreImage: Int = 0,
    @PropertyName("TaleDetails") val TaleDetails: TaleDetails = TaleDetails(),
    @PropertyName("TaleItself") val TaleItself: String = "",
    @PropertyName("TaleSummary") val TaleSummary: String = "",
    @PropertyName("TaleTitle") val TaleTitle: String = "",
    @PropertyName("userId") val userId: String = ""
)
