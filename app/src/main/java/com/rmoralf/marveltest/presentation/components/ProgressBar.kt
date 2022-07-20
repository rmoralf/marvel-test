package com.rmoralf.marveltest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rmoralf.marveltest.presentation.ui.theme.LoadingBg

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoadingBg),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}