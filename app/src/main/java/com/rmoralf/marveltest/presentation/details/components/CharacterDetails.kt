package com.rmoralf.marveltest.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rmoralf.marveltest.core.MarvelUtils
import com.rmoralf.marveltest.core.MarvelUtils.Companion.getMarvelUrlComicLink
import com.rmoralf.marveltest.core.MarvelUtils.Companion.getMarvelUrlDetail
import com.rmoralf.marveltest.core.MarvelUtils.Companion.getMarvelUrlWiki
import com.rmoralf.marveltest.core.Utils.Companion.openUrl
import com.rmoralf.marveltest.domain.model.Character

@Composable
fun CharacterDetails(
    character: Character
) {

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(MarvelUtils.composeMarvelImageUrl(character.thumbnail))
                .crossfade(true)
                .build(),
            contentDescription = character.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color.LightGray)
        )

        Text(
            text = character.name,
            color = Color.Black,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
        Text(
            text = "#${character.id}",
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (character.description.isNotEmpty()) {
            Text(
                text = character.description,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            getMarvelUrlDetail(character.urls)?.let {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                    onClick = {
                        openUrl(uriHandler, it)
                    }) {
                    Text(text = "Details")
                }
            }
            getMarvelUrlComicLink(character.urls)?.let {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                    onClick = {
                        openUrl(uriHandler, it)
                    }) {
                    Text(text = "Comics")
                }
            }
            getMarvelUrlWiki(character.urls)?.let {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                    onClick = {
                        openUrl(uriHandler, it)
                    }) {
                    Text(text = "Wiki")
                }
            }
        }
    }

}