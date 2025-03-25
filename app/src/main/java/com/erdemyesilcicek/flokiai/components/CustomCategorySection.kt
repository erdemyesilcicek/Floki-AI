package com.erdemyesilcicek.flokiai.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.ElevatedCard
import com.erdemyesilcicek.flokiai.datas.CategoryCard
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun CustomCategorySection(
    list: List<CategoryCard>,
    isMultiSelect: Boolean,
    selectedList: List<CategoryCard>,
    onSelectionChange: (CategoryCard) -> Unit,
    isGenre: Boolean = false // Add a parameter to identify genre section
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { category ->
            val isSelected = selectedList.contains(category)
            
            // Animate background color when selection state changes
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimaryContainer,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                label = "backgroundColorAnimation"
            )
            
            // Animate text color when selection state changes
            val textColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                label = "textColorAnimation"
            )
            
            // Animate scale when selection state changes
            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.1f else 1.0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "scaleAnimation"
            )
            
            ElevatedCard(
                modifier = Modifier
                    .width(100.dp)
                    .height(130.dp)
                    .scale(scale)
                    .clickable { onSelectionChange(category) },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 8.dp else 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = backgroundColor
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Apply rounded corners only to genre images
                    if (isGenre) {
                        Image(
                            painter = painterResource(id = category.image),
                            contentDescription = category.text,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(id = category.image),
                            contentDescription = category.text,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(4.dp)
                        )
                    }
                    
                    Text(
                        text = category.text,
                        fontSize = 14.sp,
                        fontFamily = myFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = textColor
                    )
                }
            }
        }
    }
}