package com.rmoralf.marveltest.domain.usecases.database

import com.rmoralf.marveltest.domain.repository.DatabaseRepository

class CheckIfCharacterIsFaved(
    private val repository: DatabaseRepository
) {
    operator fun invoke(charId: Int) = repository.checkIfCharIsFaved(charId)
}