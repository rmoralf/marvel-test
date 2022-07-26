package com.rmoralf.marveltest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rmoralf.marveltest.data.network.remote.CharactersService
import com.rmoralf.marveltest.domain.model.Response.*
import com.rmoralf.marveltest.domain.model.toDomain
import com.rmoralf.marveltest.domain.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersService
) : CharactersRepository {

    override fun searchCharacters(query: String) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { CharacterPagingSource(service, query) }
    ).flow

    override suspend fun getCharacterDetails(charId: Int) = flow {
        try {
            emit(Loading)
            val characterDetails = service
                .getCharacterDetails(characterId = charId)
                .toDomain()
                .data.results.first()
            emit(Success(characterDetails))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}