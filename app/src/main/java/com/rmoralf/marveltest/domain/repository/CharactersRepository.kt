package com.rmoralf.marveltest.domain.repository

import androidx.paging.PagingData
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun searchCharacters(query: String): Flow<PagingData<Character>>
    suspend fun getCharacterDetails(charId: Int): Flow<Response<Character>>
}