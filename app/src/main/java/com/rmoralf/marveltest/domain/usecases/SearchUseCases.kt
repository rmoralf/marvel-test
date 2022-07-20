package com.rmoralf.marveltest.domain.usecases

data class SearchUseCases(
    //Retrofit2
    val getAllCharacters: GetAllCharacters,
    val getCharacterDetails: GetCharacterDetails
)