package com.erdemyesilcicek.flokiai.datas

data class AiTale(
    val GenreImage: Int = 0,
    val TaleDetails: TaleDetails = TaleDetails(),
    val TaleItself: String = "",
    val TaleSummary: String = "",
    val EstimatedReadTimeMinutes: String = "",
    val TaleTitle: String = "",
    val userId: String = "",
    val taleId: String = ""
)
