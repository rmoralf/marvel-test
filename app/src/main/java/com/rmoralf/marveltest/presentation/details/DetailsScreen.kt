package com.rmoralf.marveltest.presentation.details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rmoralf.marveltest.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { DetailsTopBar() }
    ) {

    }
}