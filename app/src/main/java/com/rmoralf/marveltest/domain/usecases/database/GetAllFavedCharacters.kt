package com.rmoralf.marveltest.domain.usecases.database

import com.rmoralf.marveltest.domain.repository.DatabaseRepository

class GetAllFavedCharacters(
    private val repository: DatabaseRepository
) {
    operator fun invoke() = repository.getAllFavedCharacters()
}