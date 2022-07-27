package com.rmoralf.marveltest.domain.model

import com.google.common.truth.Truth
import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataContainer
import com.rmoralf.marveltest.data.network.entities.ApiCharacterDataWrapper
import org.junit.Test


class CharacterDataWrapperDataWrapperTest {

    @Test
    fun `ApiCharacterDataWrapper translates to CharacterDataWrapper`() {

        val characterDataWrapper = CharacterDataWrapper(
            code = 123,
            status = "status",
            copyright = "copyright",
            attributionText = "attributionText",
            attributionHTML = "attributionHTML",
            data = CharacterDataContainer(
                offset = 10,
                limit = 20,
                total = 30,
                count = 40,
                results = emptyList(),
            ),
            etag = "etag"
        )

        val apiCharacterDataWrapper = ApiCharacterDataWrapper(
            code = 123,
            status = "status",
            copyright = "copyright",
            attributionText = "attributionText",
            attributionHTML = "attributionHTML",
            data = ApiCharacterDataContainer(
                offset = 10,
                limit = 20,
                total = 30,
                count = 40,
                results = emptyList(),
            ),
            etag = "etag"
        )

        val newCharacterDataWrapper = apiCharacterDataWrapper.toDomain()
        Truth.assertThat(newCharacterDataWrapper).isEqualTo(characterDataWrapper)
    }

    @Test
    fun `Null ApiCharacterDataWrapper translates to empty CharacterDataWrapper`() {
        val characterDataWrapper = CharacterDataWrapper(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            data = CharacterDataContainer(
                offset = 0,
                limit = 0,
                total = 0,
                count = 0,
                results = emptyList(),
            ),
            etag = ""
        )

        val apiCharacterDataWrapper = ApiCharacterDataWrapper(
            code = null,
            status = null,
            copyright = null,
            attributionText = null,
            attributionHTML = null,
            data = null,
            etag = null,
        )

        val newCharacterDataWrapper = apiCharacterDataWrapper.toDomain()
        Truth.assertThat(newCharacterDataWrapper).isEqualTo(characterDataWrapper)
    }
}