package com.rmoralf.marveltest.presentation.search.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rmoralf.marveltest.presentation.navigation.Screen

@Composable
fun SearchTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = Screen.SEARCH.resourceId)) }
    )
}