package com.example.aplikasipeminjamanruangan.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar(onNavBack: () -> Unit, bigText: String = "", smallText: String = "",  modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onNavBack() }, modifier = Modifier
                .clip(
                    CircleShape
                )
                .background(MaterialTheme.colors.background)
                .border(
                    width = 2.dp, shape = CircleShape, color = MaterialTheme.colors.secondary
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White
            )
        }
        //Spacer(modifier = Modifier.padding(start = 28.dp))
        Text(
            color = MaterialTheme.colors.onPrimary,
            text = buildAnnotatedString {
                append("$smallText ")
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary
                    )
                ) {
                    append("$bigText")
                }
            },
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            modifier = modifier.padding(start = 24.dp)
        )
    }
}