package com.erdemyesilcicek.flokiai.pages.optionspages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erdemyesilcicek.flokiai.components.CustomText
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun LanguageSheetContent(onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(5.dp, bottom = 10.dp)
    ) {
        CustomText("Select Language")
        listOf("English - EN", "German - DE", "Turkish - TR").forEach { language ->
            TextButton(
                onClick = { onOptionSelected(language) },
                modifier = Modifier.padding(2.dp)) {
                Text(
                    text = language,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontFamily = myFont,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}