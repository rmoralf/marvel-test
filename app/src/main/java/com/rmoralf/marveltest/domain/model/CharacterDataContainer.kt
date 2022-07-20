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
    offset = offset,
    limit = limit,
    total = total,
    count = count,
    results = results.map { it.toDomain() }
)