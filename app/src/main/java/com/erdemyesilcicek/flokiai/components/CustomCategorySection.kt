package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erdemyesilcicek.flokiai.datas.CategoryCard
import com.erdemyesilcicek.flokiai.viewmodels.CategoryViewModel

@Composable
fun CustomCategorySection(
    list: List<CategoryCard>,
    isMultiple: Boolean,
    selectedList: List<CategoryCard>,
    onSelectionChange: (CategoryCard) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
    ) {
        LazyRow {
            itemsIndexed(list) { index, item ->
                val isSelected = selectedList.contains(item)
                CategoryCardItem(
                    index = index,
                    item = item,
                    isSelected = isSelected,
                    cardWidth = 100.dp,
                    cardHeight = 100.dp,
                    onClick = {
                        onSelectionChange(item)
                    }
                )
            }
        }
    }
}