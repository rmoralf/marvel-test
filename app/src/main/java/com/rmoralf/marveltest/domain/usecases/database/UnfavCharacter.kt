package com.rmoralf.marveltest.domain.usecases.database

import com.rmoralf.marveltest.data.database.entities.CharacterEntity
import com.rmoralf.marveltest.domain.repository.DatabaseRepository

class UnfavCharacter(
    private val repository: DatabaseRepository
) {
    operator fun invoke(character: CharacterEntity) = repository.unfavCharacter(character)
}