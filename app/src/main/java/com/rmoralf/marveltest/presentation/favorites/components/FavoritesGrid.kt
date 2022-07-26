package com.rmoralf.marveltest.presentation.favorites.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rmoralf.marveltest.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesGrid(
    characterList: List<Character>,
    navController: NavController
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
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
            FavoritesGridItem(character, navController)
        }
    }
}