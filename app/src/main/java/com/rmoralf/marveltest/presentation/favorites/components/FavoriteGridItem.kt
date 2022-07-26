package com.rmoralf.marveltest.presentation.favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rmoralf.marveltest.R
import com.rmoralf.marveltest.core.MarvelUtils.Companion.composeMarvelImageUrl
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.presentation.navigation.Screen

@Composable
fun FavoritesGridItem(
    character: Character,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "${Screen.DETAILS.route}/${character.id}"
                )
            }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(composeMarvelImageUrl(character.thumbnail))
                    .crossfade(true)
                    .build(),
                contentDescription = character.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGray)
                    .aspectRatio(1.2f)
            )
            Icon(
                Icons.Default.Favorite,
                stringResource(id = R.string.topapp_fav),
                tint = LightGray,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
            )
        }
        Text(
            text = character.name,
            color = Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        )
    }
}