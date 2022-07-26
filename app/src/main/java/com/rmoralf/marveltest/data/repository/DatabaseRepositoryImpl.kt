package com.rmoralf.marveltest.data.repository

import com.rmoralf.marveltest.data.database.dao.CharacterDao
import com.rmoralf.marveltest.data.database.entities.CharacterEntity
import com.rmoralf.marveltest.domain.model.Response.*
import com.rmoralf.marveltest.domain.model.toDomain
import com.rmoralf.marveltest.domain.repository.DatabaseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class DatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : DatabaseRepository {

    override fun favCharacter(character: CharacterEntity) = flow {
        try {
            emit(Loading)
            val store = characterDao.insert(character)
            emit(Success(store))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun unfavCharacter(character: CharacterEntity) = flow {
        try {
            emit(Loading)
            val deletion = characterDao.delete(character)
            emit(Success(deletion))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun getAllFavedCharacters() = flow {
        try {
            emit(Loading)
            val allChars = characterDao.getAllFavedCharacters().map { it.toDomain() }
            emit(Success(allChars))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun checkIfCharIsFaved(characterId: Int) = flow {
        try {
            emit(Loading)
            val isFaved = characterDao.getByIds(intArrayOf(characterId)).isNotEmpty()
            emit(Success(isFaved))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}