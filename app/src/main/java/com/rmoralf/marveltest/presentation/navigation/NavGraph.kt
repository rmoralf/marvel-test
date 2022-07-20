package com.rmoralf.marveltest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.rmoralf.marveltest.core.Constants.CHAR_ID
import com.rmoralf.marveltest.core.Utils.Companion.createSubroute
import com.rmoralf.marveltest.presentation.details.DetailsScreen
import com.rmoralf.marveltest.presentation.search.SearchScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoilApi
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SEARCH.route,
    ) {
/*
        composable(route = Screen.MENU.route) {
            MenuScreen(navController = navController)
        }
*/

        composable(route = Screen.SEARCH.route) {
            SearchScreen(navController = navController)
        }

        composable(
            route = createSubroute(Screen.DETAILS.route, CHAR_ID)
        ) {
            DetailsScreen(navController = navController)
        }

/*
        composable(route = Screen.FAVORITES.route) {
            FavoritesScreen(navController = navController)
        }
*/
    }
}