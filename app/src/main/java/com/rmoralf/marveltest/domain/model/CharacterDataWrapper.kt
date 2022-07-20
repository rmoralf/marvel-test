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
    code = code,
    status = status,
    copyright = copyright,
    attributionText = attributionText,
    attributionHTML = attributionHTML,
    data = data.toDomain(),
    etag = etag
)