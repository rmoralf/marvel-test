package com.rmoralf.marveltest.domain.usecases.search

import com.rmoralf.marveltest.domain.repository.CharactersRepository

class SearchCharacters(
    private val repository: CharactersRepository
) {
    operator fun invoke(nameStartsWith: String) = repository.searchCharacters(nameStartsWith)
}