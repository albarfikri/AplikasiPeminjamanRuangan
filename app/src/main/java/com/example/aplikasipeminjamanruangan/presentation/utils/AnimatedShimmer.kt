package com.example.aplikasipeminjamanruangan.presentation.utils

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AnimateShimmer(modifier: Modifier) {
    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )

    LazyRow (userScrollEnabled = false) {
        items(6) {
            ItemCard(modifier = Modifier, brush = brush)
        }

    }
}


@Composable
fun ItemCard(
    modifier: Modifier,
    brush: Brush
) {
    Card(
        modifier = Modifier
            .height(265.dp)
            .width(200.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp))
            .background(brush),
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
        elevation = 10.dp
    ) {
        Column {

            AsyncImage(
                modifier = Modifier
                    .height(180.dp)
                    .background(brush),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("")
                    .crossfade(true)
                    .build(),
                contentDescription = "namaRuangan",
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(fraction = 0.9f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(brush)
                )
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(fraction = 0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(brush)
                )
            }
        }
    }
}