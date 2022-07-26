package com.rmoralf.marveltest.data.network.remote

import com.rmoralf.marveltest.core.Constants.Network.QUERY_LIMIT
import com.rmoralf.marveltest.core.Constants.Network.QUERY_NAME_STARTS_WITH
import com.rmoralf.marveltest.core.Constants.Network.QUERY_OFFSET
import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(QUERY_OFFSET) offset: Int? = 0,
        @Query(QUERY_LIMIT) limit: Int? = 20
    ): ApiCharacterDataWrapper

    @GET("/v1/public/characters")
    suspend fun searchCharacters(
        @Query(QUERY_OFFSET) offset: Int? = 0,
        @Query(QUERY_LIMIT) limit: Int? = 20,
        @Query(QUERY_NAME_STARTS_WITH) nameStartsWith: String
    ): ApiCharacterDataWrapper

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int
    ): ApiCharacterDataWrapper
}