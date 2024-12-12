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

@Composable
fun CustomCategorySection(list: List<CategoryCard>, isMultiple: Boolean) {
    val selectedButtons = remember { mutableStateListOf<Int>() }

    Box(
        modifier = Modifier
            .padding(5.dp)
    ) {
        LazyRow {
            itemsIndexed(list) { index, item ->
                CategoryCardItem(
                    index = index,
                    item = item,
                    isSelected = selectedButtons.contains(index),
                    cardWidth = 100.dp,
                    cardHeight = 100.dp,
                    onClick = {
                        if (selectedButtons.contains(index)) {
                            selectedButtons.remove(index)
                        } else {
                            if (isMultiple && selectedButtons.size < 3) {
                                selectedButtons.add(index)
                            } else if (!isMultiple && selectedButtons.size < 1) {
                                selectedButtons.add(index)
                            }
                        }
                    }
                )
            }
        }
    }
}