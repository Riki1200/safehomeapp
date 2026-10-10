/*
 *
 *  *
 *  *  * Copyright (c) 2026
 *  *  *
 *  *  * Author: Athar Gul
 *  *  * GitHub: https://github.com/DevAtrii/Kmp-Starter-Template
 *  *  * YouTube: https://www.youtube.com/@devatrii/videos
 *  *  *
 *  *  * All rights reserved.
 *  *
 *  *
 *
 */

package com.romeodev.safehomeapp.feature_core_presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_core_presentation.viewmodels.SplashEvents
import com.romeodev.safehomeapp.feature_core_presentation.viewmodels.SplashViewModel
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_logo
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Suppress("ParamsComparedByRef")
@Composable
fun SplashScreen(
    onNavigate: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val viewModel: SplashViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(
        flow = viewModel.uiEvents
    ) { event ->
        when (event) {
            SplashEvents.OnFinish -> {
                if (state.isOnboarded)
                    onNavigate()
                else
                    onNavigateToOnboarding()
            }
        }
    }

    SplashScreenContent(
        modifier = Modifier
    )
}


// Matches the native splash background (Android theme + iOS LaunchBackground)
private val SplashBackground = Color(0xFF0A2540)

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {
    val scale = remember { Animatable(0.5f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 600)
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.safehome_logo),
            contentDescription = "SafeHome logo",
            modifier = Modifier
                .size(200.dp)
                .scale(scale.value)
                .alpha(alpha.value)
        )
    }
}
