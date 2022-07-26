package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataWrapper

data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharacterDataContainer,
    val etag: String
)

fun ApiCharacterDataWrapper.toDomain() = CharacterDataWrapper(
    code = code ?: 0,
    status = status.orEmpty(),
    copyright = copyright.orEmpty(),
    attributionText = attributionText.orEmpty(),
    attributionHTML = attributionHTML.orEmpty(),
    data = data?.toDomain() ?: CharacterDataContainer(0, 0, 0, 0, emptyList()),
    etag = etag.orEmpty()
)