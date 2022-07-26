package com.rmoralf.marveltest.presentation.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.presentation.navigation.Screen

@Composable
fun MenuScreen(
    navController: NavController
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painterResource(R.drawable.marvel_logo),
                stringResource(id = R.string.marvel_logo),
                modifier = Modifier.padding(32.dp)
            )

            Button(modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 32.dp),
                onClick = {
                    navController.navigate(
                        Screen.SEARCH.route
                    )
                }) {
                Text(text = stringResource(id = R.string.screen_search))
            }
            Button(modifier = Modifier
                .align(CenterHorizontally),
                onClick = {
                    navController.navigate(
                        Screen.FAVORITES.route
                    )
                }) {
                Text(text = stringResource(id = R.string.screen_favorites))
            }
        }
    }
}