package com.rmoralf.marveltest.domain.usecases

import com.rmoralf.marveltest.domain.repository.CharactersRepository


class GetCharacterDetails(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(charId: Int) = repository.getCharacterDetails(charId = charId)
}