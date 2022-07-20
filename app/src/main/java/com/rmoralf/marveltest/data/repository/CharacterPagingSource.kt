package com.rmoralf.marveltest.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rmoralf.marveltest.data.network.remote.CharactersService
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.toDomain
import retrofit2.HttpException

private const val STARTING_OFFSET = 0
private const val LOAD_SIZE = 20

class CharacterPagingSource(
    private val charactersService: CharactersService,
//    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = (params.key ?: STARTING_OFFSET)
        return try {
            val response =
                charactersService.getAllCharacters(offset = position, limit = LOAD_SIZE)
            val data = response.data
            val characters = data.results.map { it.toDomain() }

            LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_OFFSET) null else position - LOAD_SIZE,
                nextKey = if (characters.isEmpty()) null else position + LOAD_SIZE
            )
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null

}