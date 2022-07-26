package com.rmoralf.marveltest.data.network.entities

data class ApiCharacterDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: ApiCharacterDataContainer?,
    val etag: String?
)