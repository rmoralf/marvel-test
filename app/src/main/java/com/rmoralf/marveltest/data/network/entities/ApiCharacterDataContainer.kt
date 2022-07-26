package com.rmoralf.marveltest.data.network.entities

data class ApiCharacterDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<ApiCharacter>?
)