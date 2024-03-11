package com.example.pexelapp.presentation.screen.SplashScreen

import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.pexelapp.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )

    val window = (LocalContext.current as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.tertiary.toArgb()
    window.navigationBarColor = MaterialTheme.colorScheme.tertiary.toArgb()

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alpha = alphaAnim.value)
}