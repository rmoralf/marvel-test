package com.rmoralf.marveltest.data.network.entities

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String,
    val thumbnail: ApiImage,
    val urls: List<ApiUrl>

)
