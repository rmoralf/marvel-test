package com.rmoralf.marveltest.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rmoralf.marveltest.core.Utils.Companion.printError
import com.rmoralf.marveltest.presentation.components.ProgressBar
import com.rmoralf.marveltest.presentation.search.components.EmptySearchListText
import com.rmoralf.marveltest.presentation.search.components.SearchGrid
import com.rmoralf.marveltest.presentation.search.components.SearchRow
import com.rmoralf.marveltest.presentation.search.components.SearchTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val characterList = viewModel.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = { SearchTopBar(navController) }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchRow()

                if (characterList.itemCount > 0) {
                    SearchGrid(characterList = characterList, navController = navController)
                } else {
                    EmptySearchListText()
                }
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