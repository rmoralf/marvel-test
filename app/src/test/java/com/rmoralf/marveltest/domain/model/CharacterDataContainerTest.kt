package com.rmoralf.marveltest.domain.model

import com.google.common.truth.Truth
import com.rmoralf.marveltest.data.network.entities.ApiCharacter
import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataContainer
import com.rmoralf.marveltest.data.network.entities.ApiImage
import org.junit.Test


class CharacterDataContainerDataContainerTest {
    @Test
    fun `ApiCharacterDataContainerDataContainer translates to CharacterDataContainer`() {

        val characterDataContainer = CharacterDataContainer(
            offset = 10,
            limit = 20,
            total = 30,
            count = 40,
            results = listOf(
                Character(
                    id = 0,
                    name = "",
                    description = "",
                    resourceURI = "",
                    thumbnail = Image("", ""),
                    urls = emptyList()
                )
            )
        )

        val apiCharacterDataContainer = ApiCharacterDataContainer(
            offset = 10,
            limit = 20,
            total = 30,
            count = 40,
            results = listOf(
                ApiCharacter(
                    id = 0,
                    name = "",
                    description = "",
                    resourceURI = "",
                    thumbnail = ApiImage("", ""),
                    urls = emptyList()
                )
            )
        )

        val newCharacterDataContainer = apiCharacterDataContainer.toDomain()
        Truth.assertThat(newCharacterDataContainer).isEqualTo(characterDataContainer)
    }

    @Test
    fun `Null ApiCharacterDataContainer translates to empty CharacterDataContainer`() {
        val characterDataContainer = CharacterDataContainer(
            offset = 0,
            limit = 0,
            total = 0,
            count = 0,
            results = emptyList()
        )

        val apiCharacterDataContainer = ApiCharacterDataContainer(
            offset = null,
            limit = null,
            total = null,
            count = null,
            results = null
        )

        val newCharacterDataContainer = apiCharacterDataContainer.toDomain()
        Truth.assertThat(newCharacterDataContainer).isEqualTo(characterDataContainer)
    }

}