package com.rmoralf.marveltest.presentation.favorites.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.presentation.navigation.Screen

@Composable
fun FavoritesTopBar(
    navController: NavController,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = Screen.FAVORITES.resourceId)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.topapp_back),
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    )
}