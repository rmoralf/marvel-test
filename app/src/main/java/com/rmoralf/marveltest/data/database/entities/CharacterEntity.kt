package com.rmoralf.marveltest.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rmoralf.marveltest.core.Constants.DbConstants.TABLE_NAME
import com.rmoralf.marveltest.domain.model.Character

@Entity(tableName = TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val thumbnail_path: String?,
    val thumbnail_ext: String?
)

fun Character.toDatabase() = CharacterEntity(
    id = id,
    name = name,
    thumbnail_path = thumbnail.path,
    thumbnail_ext = thumbnail.extension
)