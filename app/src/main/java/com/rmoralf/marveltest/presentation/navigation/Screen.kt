package com.rmoralf.marveltest.presentation.navigation

import androidx.annotation.StringRes
import com.rmoralf.marveltest.R

enum class Screen(val route: String, @StringRes val resourceId: Int) {
    MENU("menu", R.string.screen_menu),
    SEARCH("search", R.string.screen_search),
    DETAILS("details", R.string.screen_details),
    FAVORITES("favorites", R.string.screen_favorites),
}