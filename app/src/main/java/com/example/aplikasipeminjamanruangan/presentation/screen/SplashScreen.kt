package com.example.aplikasipeminjamanruangan.presentation.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.aplikasipeminjamanruangan.R
import com.example.aplikasipeminjamanruangan.presentation.navigation.Home
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(navController: NavHostController, modifier: Modifier) {
    val splashEndDelay = 2200.milliseconds
    val animEndDelay = 1300

    var isSplashScreenStart by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (isSplashScreenStart) 1f else 0f,
        animationSpec = tween(
            durationMillis = animEndDelay
        )
    )

    LaunchedEffect(key1 = true) {
        isSplashScreenStart = true
        delay(splashEndDelay)
        navController.popBackStack()
        navController.navigate(Home.route)
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.history))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isSplashScreenStart,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = modifier.alpha(alphaAnim.value)
        )
        Text(
            text = stringResource(id = R.string.splash_text), style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onPrimary,
            modifier = modifier.alpha(alphaAnim.value)
        )
        Text(
            text = stringResource(id = R.string.splash_caption),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary,
            modifier = modifier.alpha(alphaAnim.value)
        )
    }
}