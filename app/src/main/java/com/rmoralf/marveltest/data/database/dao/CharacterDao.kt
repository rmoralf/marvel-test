package com.rmoralf.marveltest.data.database.dao

import androidx.room.*
import com.rmoralf.marveltest.core.Constants.DbConstants.TABLE_NAME
import com.rmoralf.marveltest.data.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllFavedCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE id IN (:characterDBIds)")
    suspend fun getByIds(characterDBIds: IntArray): List<CharacterEntity>

}