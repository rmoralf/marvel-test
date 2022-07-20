package com.rmoralf.marveltest.domain.model

import com.rmoralf.marveltest.data.network.entities.ApiImage

data class Image(
    val path: String,
    val extension: String
)

fun ApiImage.toDomain() = Image(
    path = path,
    extension = extension
)