package com.rmoralf.marveltest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmoralf.marveltest.core.Constants.DbConstants.DB_VERSION
import com.rmoralf.marveltest.data.database.dao.CharacterDao
import com.rmoralf.marveltest.data.database.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = DB_VERSION, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}