package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.network.entities.ApiCharacter

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String,
    val thumbnail: Image,
    val urls: List<Url>
)

fun ApiCharacter.toDomain() = Character(
    id = id,
    name = name,
    description = description,
    resourceURI = resourceURI,
    thumbnail = thumbnail.toDomain(),
    urls = urls.map { it.toDomain() }
)
