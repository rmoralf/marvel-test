package com.rmoralf.marveltest.domain.usecases.search

data class SearchUseCases(
    //Retrofit2
    val searchCharacters: SearchCharacters,
    val getCharacterDetails: GetCharacterDetails
)