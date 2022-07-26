package com.rmoralf.marveltest.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.presentation.ui.theme.LoadingBg

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoadingBg),
        contentAlignment = Alignment.Center
    ) {

        val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing)
            )
        )
        Image(
            painterResource(R.drawable.cptamericashield),
            stringResource(id = R.string.loading_logo),
            modifier = Modifier
                .size(64.dp)
                .graphicsLayer {
                    rotationZ = angle
                }
        )


    }
}