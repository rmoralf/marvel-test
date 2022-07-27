package com.rmoralf.marveltest.domain.model

import com.google.common.truth.Truth
import com.rmoralf.marveltest.data.network.entities.ApiImage
import org.junit.Test


class ImageTest{

    @Test
    fun `ApiImage translates to Image`() {

        val image = Image(
            path = "path",
            extension = "extension"
        )

        val apiImage = ApiImage(
            path = "path",
            extension = "extension"
        )

        val newImage = apiImage.toDomain()

        Truth.assertThat(newImage).isEqualTo(image)
    }

    @Test
    fun `Null ApiImage translates to empty Image`(){
        val image = Image(
            path = "",
            extension = ""
        )

        val apiImage = ApiImage(
            path = null,
            extension = null
        )

        val newImage = apiImage.toDomain()

        Truth.assertThat(newImage).isEqualTo(image)
    }
}