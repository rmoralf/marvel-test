package com.rmoralf.marveltest.core

object Constants {
    //App
    const val TAG = "AppTag"

    //Retrofit2
    const val API_ENDPOINT = "https://gateway.marvel.com/"
    const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
    const val QUERY_TS = "ts"
    const val QUERY_APIKEY = "apikey"
    const val QUERY_HASH = "hash"

    const val QUERY_OFFSET = "offset"
    const val QUERY_LIMIT = "limit"
    const val QUERY_NAME_STARTS_WITH = "nameStartsWith"

    //Routes
    const val CHAR_ID = "characterId"

}
