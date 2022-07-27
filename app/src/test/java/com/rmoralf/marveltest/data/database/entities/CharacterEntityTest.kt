package com.rmoralf.marveltest.data.database.entities

import com.google.common.truth.Truth.assertThat
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.Image
import org.junit.Test

class CharacterEntityTest {

    private val characterEntity = CharacterEntity(
        id = 1,
        name = "Hulk",
        thumbnail_path = "thumbnail_path",
        thumbnail_ext = "thumbnail_ext"
    )

    private val character = Character(
        id = 1,
        name = "Hulk",
        thumbnail = Image(
            path = "thumbnail_path",
            extension = "thumbnail_ext"
        ),
        description = "",
        resourceURI = "",
        urls = emptyList()
    )

    @Test
    fun `Character translates to database object CharacterEntity`() {
        val newCharacterEntity = character.toDatabase()
        assertThat(newCharacterEntity).isEqualTo(characterEntity)
    }
}