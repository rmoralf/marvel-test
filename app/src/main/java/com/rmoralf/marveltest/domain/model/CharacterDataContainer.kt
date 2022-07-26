package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataContainer

data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)

fun ApiCharacterDataContainer.toDomain() = CharacterDataContainer(
    offset = offset ?: 0,
    limit = limit ?: 0,
    total = total ?: 0,
    count = count ?: 0,
    results = results?.map { it.toDomain() }.orEmpty()
)