package com.rmoralf.marveltest.domain.repository

import com.rmoralf.marveltest.data.database.entities.CharacterEntity
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun favCharacter(character: CharacterEntity): Flow<Response<Unit>>
    fun unfavCharacter(character: CharacterEntity): Flow<Response<Unit>>
    fun getAllFavedCharacters(): Flow<Response<List<Character>>>
    fun checkIfCharIsFaved(characterId: Int): Flow<Response<Boolean>>
}