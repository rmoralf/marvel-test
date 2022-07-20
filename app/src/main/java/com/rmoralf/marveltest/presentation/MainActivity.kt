package com.rmoralf.marveltest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.rmoralf.marveltest.presentation.navigation.NavGraph
import com.rmoralf.marveltest.presentation.ui.theme.MarvelTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoilApi
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelTestTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController
                )
            }
        }
    }
}