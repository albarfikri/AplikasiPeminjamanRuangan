package com.example.aplikasipeminjamanruangan.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(color = MaterialTheme.colors.onPrimary, text = buildAnnotatedString {
            append("History ")
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            ) {
                append("List")
            }
        })
    }
}