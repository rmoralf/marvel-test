package com.rmoralf.marveltest.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rmoralf.marveltest.core.Utils.Companion.printError
import com.rmoralf.marveltest.presentation.components.ProgressBar
import com.rmoralf.marveltest.presentation.search.components.CharacterGrid
import com.rmoralf.marveltest.presentation.search.components.EmptyListText
import com.rmoralf.marveltest.presentation.search.components.SearchTopBar

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val characterList = viewModel.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = { SearchTopBar() }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (characterList.itemCount > 0) {
                CharacterGrid(characterList = characterList, navController = navController)
            } else {
                EmptyListText()
            }

            characterList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> ProgressBar()
                    loadState.refresh is LoadState.Error -> printError(characterList.loadState.refresh as LoadState.Error)
                    loadState.append is LoadState.Loading -> ProgressBar()
                    loadState.append is LoadState.Error -> printError(characterList.loadState.append as LoadState.Error)
                }
            }
        }
    }
}