package com.example.aplikasipeminjamanruangan.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.aplikasipeminjamanruangan.presentation.components.Calendar
import com.example.aplikasipeminjamanruangan.presentation.components.Clock
import com.maxkeppeker.sheets.core.models.base.rememberSheetState

@Composable
fun CustomDialog(
    onDismiss: () -> Unit, onConfirm: (List<String>) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }, properties = DialogProperties(
            usePlatformDefaultWidth = true
        )
    ) {
        var tanggalValue by rememberSaveable { mutableStateOf("") }
        var jMulaiValue by rememberSaveable { mutableStateOf("") }
        var jSelesaiValue by rememberSaveable { mutableStateOf("") }

        val calendarState = rememberSheetState()
        val beginClockState = rememberSheetState()
        val endClockState = rememberSheetState()

        Calendar(calendarState = calendarState, dateValue = {
            tanggalValue = it.toString()
        })

        Clock(clockState = beginClockState, clockValue = {
            jMulaiValue = it
        })

        Clock(clockState = endClockState, clockValue = {
            jSelesaiValue = it
        })

        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Text(
                    text = "Mau cari ruangan yang mana ?",
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    color = textColor,
                    fontSize = 19.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(18.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(imageVector = Icons.Filled.CalendarToday,
                            contentDescription = null,
                            tint = textColor,
                            modifier = Modifier.clickable { calendarState.show() })
                        Text(
                            text = "Calendar", fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = tanggalValue.ifEmpty { "tekan disini" },
                            color = if (tanggalValue.isEmpty()) Color.LightGray else textColor,
                            modifier = Modifier.clickable { calendarState.show() }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(imageVector = Icons.Filled.Schedule,
                            contentDescription = null,
                            tint = textColor,
                            modifier = Modifier.clickable { beginClockState.show() })
                        Text(
                            text = "Mulai", fontWeight = FontWeight.Bold,
                            color = textColor, modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = jMulaiValue.ifEmpty { "tekan disini" },
                            color = if (jMulaiValue.isEmpty()) Color.LightGray else textColor,
                            modifier = Modifier.clickable { beginClockState.show() }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(imageVector = Icons.Filled.TimerOff,
                            contentDescription = null,
                            tint = textColor,
                            modifier = Modifier.clickable { endClockState.show() })
                        Text(
                            text = "Selesai",
                            fontWeight = FontWeight.Bold,
                            color = textColor, modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = jSelesaiValue.ifEmpty { "tekan disini" },
                            color = if (jSelesaiValue.isEmpty()) Color.LightGray else textColor,
                            modifier = Modifier.clickable { endClockState.show() }
                        )
                    }
                    Divider()
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        }, colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                        ), modifier = Modifier, shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Button(
                        onClick = {
                            val data = listOf(tanggalValue, jMulaiValue, jSelesaiValue)
                            onConfirm.invoke(data)
                        }, colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                        ), modifier = Modifier, shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }

            }
        }
    }
}