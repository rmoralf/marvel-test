package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.network.entities.ApiUrl

data class Url(
    val type: String,
    val url: String
)

fun ApiUrl.toDomain() = Url(
    type = type.orEmpty(),
    url = url.orEmpty()
)