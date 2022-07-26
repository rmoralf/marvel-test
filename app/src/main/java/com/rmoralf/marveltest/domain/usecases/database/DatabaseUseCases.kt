package com.rmoralf.marveltest.domain.usecases.database

data class DatabaseUseCases(
    //Retrofit2
    val favCharacter: FavCharacter,
    val unfavCharacter: UnfavCharacter,
    val getAllFavedCharacters: GetAllFavedCharacters,
    val checkIfCharacterIsFaved: CheckIfCharacterIsFaved
)