package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.database.entities.CharacterEntity
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
    id = id ?: 0,
    name = name.orEmpty(),
    description = description.orEmpty(),
    resourceURI = resourceURI.orEmpty(),
    thumbnail = thumbnail?.toDomain() ?: Image("", ""),
    urls = urls?.map { it.toDomain() }.orEmpty()
)

fun CharacterEntity.toDomain() = Character(
    id = id,
    name = name.orEmpty(),
    thumbnail = Image(
        path = thumbnail_path.orEmpty(),
        extension = thumbnail_ext.orEmpty()
    ),
    description = String(),
    resourceURI = String(),
    urls = emptyList()
)