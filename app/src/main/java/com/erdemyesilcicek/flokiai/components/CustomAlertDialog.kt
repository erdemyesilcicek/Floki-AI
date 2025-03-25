package com.erdemyesilcicek.flokiai.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.erdemyesilcicek.flokiai.utils.myFont

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    buttonText: String,
    buttonColor: Color,
    onButtonClick: () -> Unit,
    onDismiss: () -> Unit,
    isDoubleButton: Boolean = false,
    secondButtonText: String = "",
    secondButtonColor: Color = Color.Transparent,
    secondButtonOnClick: () -> Unit = {},
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = myFont,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    fontFamily = myFont,
                    modifier = Modifier.fillMaxWidth()
                )

                if (isDoubleButton) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = secondButtonColor
                            ),
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, secondButtonColor),
                            onClick = { secondButtonOnClick() }
                        ) {
                            Text(
                                text = secondButtonText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = myFont,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        
                        Button(
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor
                            ),
                            shape = RoundedCornerShape(20.dp),
                            onClick = { onButtonClick() }
                        ) {
                            Text(
                                text = buttonText,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = myFont,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        shape = RoundedCornerShape(20.dp),
                        onClick = { onButtonClick() }
                    ) {
                        Text(
                            text = buttonText,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = myFont,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}