package com.rmoralf.marveltest.domain.usecases

import com.rmoralf.marveltest.domain.repository.CharactersRepository

class GetAllCharacters(
    private val repository: CharactersRepository
) {
    operator fun invoke() = repository.getAllCharacters()
}