package com.rmoralf.marveltest.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rmoralf.marveltest.core.Utils.Companion.printError
import com.rmoralf.marveltest.domain.model.Response.*
import com.rmoralf.marveltest.presentation.components.ProgressBar
import com.rmoralf.marveltest.presentation.details.components.CharacterDetails
import com.rmoralf.marveltest.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            DetailsTopBar(navController)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            when (val comicResponse = viewModel.state.value) {
                is Loading -> ProgressBar()
                is Success -> {
                    val character = comicResponse.data
                    viewModel.currentCharacter = character
                    viewModel.checkIfCharacterIsFaved(character.id)
                    CharacterDetails(character = character)
                }
                is Error -> printError(comicResponse.message)
            }

            when (val favUnfavResponse = viewModel.favUnfavState.value) {
                is Error -> printError(favUnfavResponse.message)
                else -> {}
            }
        }
    }
}