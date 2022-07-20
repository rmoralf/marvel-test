package com.rmoralf.marveltest.core

import com.rmoralf.marveltest.BuildConfig
import com.rmoralf.marveltest.domain.model.Image
import com.rmoralf.marveltest.domain.model.Url
import java.math.BigInteger
import java.security.MessageDigest

class MarvelUtils {

    companion object {

        private const val URLS_DETAIL = "detail"
        private const val URLS_WIKI = "wiki"
        private const val URLS_COMIC_LINK = "comiclink"

        const val API_KEY = BuildConfig.PUBLIC_KEY
        private const val PRIVATE_KEY = BuildConfig.PRIVATE_KEY
        val TS = System.currentTimeMillis().toString()

        fun marvelHash(): String {
            val input = TS + PRIVATE_KEY + API_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }

        fun getMarvelUrlDetail(urls: List<Url>): String? {
            return urls.find { it.type == URLS_DETAIL }?.url
        }

        fun getMarvelUrlWiki(urls: List<Url>): String? {
            return urls.find { it.type == URLS_WIKI }?.url
        }

        fun getMarvelUrlComicLink(urls: List<Url>): String? {
            return urls.find { it.type == URLS_COMIC_LINK }?.url
        }

        fun composeMarvelImageUrl(thumbnail: Image?): String {
            return thumbnail?.let { thumbnail.path + "." + thumbnail.extension } ?: String()
        }

    }
}
