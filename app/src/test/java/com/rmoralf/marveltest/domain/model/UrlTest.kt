package com.rmoralf.marveltest.domain.model

import com.google.common.truth.Truth.assertThat
import com.rmoralf.marveltest.data.network.entities.ApiUrl
import org.junit.Test

class UrlTest {

    @Test
    fun `ApiUrl translates to Url`() {

        val url = Url(
            type = "type",
            url = "http://www.google.com"
        )

        val apiUrl = ApiUrl(
            type = "type",
            url = "http://www.google.com"
        )

        val newUrl = apiUrl.toDomain()

        assertThat(newUrl).isEqualTo(url)
    }

    @Test
    fun `Null ApiUrl translates to empty Url`() {
        val url = Url(
            type = "",
            url = ""
        )

        val apiUrl = ApiUrl(
            type = null,
            url = null
        )

        val newUrl = apiUrl.toDomain()

        assertThat(newUrl).isEqualTo(url)
    }
}