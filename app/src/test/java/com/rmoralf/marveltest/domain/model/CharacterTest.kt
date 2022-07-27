package com.rmoralf.marveltest.domain.model

import com.google.common.truth.Truth
import com.rmoralf.marveltest.data.network.entities.ApiCharacter
import com.rmoralf.marveltest.data.network.entities.ApiImage
import com.rmoralf.marveltest.data.network.entities.ApiUrl
import org.junit.Test


class CharacterTest {

    @Test
    fun `ApiCharacter translates to Character`() {

        val character = Character(
            id = 123,
            name = "name",
            description = "description",
            resourceURI = "resourceURI",
            thumbnail = Image("path", "extension"),
            urls = listOf(Url("type", "url"))
        )

        val apiCharacter = ApiCharacter(
            id = 123,
            name = "name",
            description = "description",
            resourceURI = "resourceURI",
            thumbnail = ApiImage("path", "extension"),
            urls = listOf(ApiUrl("type", "url"))
        )

        val newCharacter = apiCharacter.toDomain()
        Truth.assertThat(newCharacter).isEqualTo(character)
    }

    @Test
    fun `Null ApiCharacter translates to empty Character`() {
        val character = Character(
            id = 0,
            name = "",
            description = "",
            resourceURI = "",
            thumbnail = Image("", ""),
            urls = emptyList()
        )

        val apiCharacter = ApiCharacter(
            id = null,
            name = null,
            description = null,
            resourceURI = null,
            thumbnail = null,
            urls = null
        )

        val newCharacter = apiCharacter.toDomain()
        Truth.assertThat(newCharacter).isEqualTo(character)
    }

}