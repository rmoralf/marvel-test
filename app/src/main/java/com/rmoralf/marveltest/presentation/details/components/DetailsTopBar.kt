package com.rmoralf.marveltest.presentation.details.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.core.Utils.Companion.printError
import com.rmoralf.marveltest.domain.model.Response
import com.rmoralf.marveltest.presentation.details.DetailsViewModel
import com.rmoralf.marveltest.presentation.navigation.Screen

@Composable
fun DetailsTopBar(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    TopAppBar(
        title = { Text(text = stringResource(id = Screen.DETAILS.resourceId)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.topapp_back),
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        },

        actions = {
            when (val isFav = viewModel.isFavState.value) {
                is Response.Success -> {
                    if (isFav.data) {
                        IconButton(onClick = {
                            viewModel.unfavCharacter()
                        }) {
                            Icon(
                                Icons.Default.Favorite,
                                stringResource(id = R.string.topapp_unfav)
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.favCharacter()
                        }) {
                            Icon(
                                Icons.Default.FavoriteBorder,
                                stringResource(id = R.string.topapp_fav)
                            )
                        }

                    }
                }
                is Response.Error -> printError(isFav.message)
                else -> {}
            }
        }
    )
}