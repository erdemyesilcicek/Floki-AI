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
import androidx.compose.foundation.layout.wrapContentWidth
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
                .padding(2.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Red)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = message,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                if (isDoubleButton) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(80.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            onClick = { secondButtonOnClick() }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Text(
                                    color = secondButtonColor,
                                    text = secondButtonText,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = myFont
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(80.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor
                            ),
                            onClick = { onButtonClick() }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Text(
                                    color = Color.White,
                                    text = buttonText,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = myFont
                                )
                            }
                        }
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(80.dp)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = buttonColor
                        ),
                        onClick = { onButtonClick() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Text(
                                color = Color.White,
                                text = buttonText,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = myFont
                            )
                        }
                    }
                }
            }
        }
    }
}