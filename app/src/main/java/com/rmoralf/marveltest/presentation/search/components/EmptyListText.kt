package com.rmoralf.marveltest.presentation.search.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.rmoralf.marveltest.R

@Composable
fun EmptyListText() {
    Text(
        text = stringResource(id = R.string.empty_results_list),
        textAlign = TextAlign.Center,
    )
}