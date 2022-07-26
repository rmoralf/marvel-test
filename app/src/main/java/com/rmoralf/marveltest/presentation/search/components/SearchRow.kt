package com.rmoralf.marveltest.presentation.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.presentation.search.SearchViewModel

@Composable
fun SearchRow(
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchTxt by remember { mutableStateOf(viewModel.lastSearch) }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchTxt,
            onValueChange = { searchTxt = it },
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_screen_textfield)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
        )
        IconButton(
            onClick = {
                viewModel.searchCharacter(searchTxt)
                focusManager.clearFocus()
            }
        ) {
            Icon(
                Icons.Default.Search,
                stringResource(id = R.string.search_screen_btn)
            )
        }
    }
}