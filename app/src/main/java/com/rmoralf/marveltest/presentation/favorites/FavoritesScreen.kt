package com.rmoralf.marveltest.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rmoralf.marveltest.core.Utils
import com.rmoralf.marveltest.domain.model.Response
import com.rmoralf.marveltest.presentation.components.ProgressBar
import com.rmoralf.marveltest.presentation.favorites.components.EmptyFavListText
import com.rmoralf.marveltest.presentation.favorites.components.FavoritesGrid
import com.rmoralf.marveltest.presentation.favorites.components.FavoritesTopBar

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { FavoritesTopBar(navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val comicResponse = viewModel.state.value) {
                is Response.Loading -> ProgressBar()
                is Response.Success -> {
                    val characterList = comicResponse.data
                    if (characterList.isNotEmpty()) {
                        FavoritesGrid(characterList = characterList, navController = navController)
                    } else {
                        EmptyFavListText()
                    }
                }
                is Response.Error -> Utils.printError(comicResponse.message)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllFavedCharacters()
    }
}