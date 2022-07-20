package com.rmoralf.marveltest.presentation.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.rmoralf.marveltest.core.items
import com.rmoralf.marveltest.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterGrid(
    characterList: LazyPagingItems<Character>,
    navController: NavController
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
//        cells = GridCells.Adaptive(128.dp),
        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(characterList) { character ->
            character?.let {
                CharacterGridItem(it, navController)
            }
        }
    }
}