package com.rmoralf.marveltest.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmoralf.marveltest.core.Constants.Routes.CHAR_ID
import com.rmoralf.marveltest.data.database.entities.toDatabase
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.Response
import com.rmoralf.marveltest.domain.usecases.database.DatabaseUseCases
import com.rmoralf.marveltest.domain.usecases.search.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases,
    private val databaseUseCases: DatabaseUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<Response<Character>>(Response.Loading)
    val state: State<Response<Character>> = _state

    private val _isFavState = mutableStateOf<Response<Boolean>>(Response.Loading)
    val isFavState: State<Response<Boolean>> = _isFavState

    private val _favUnfavState = mutableStateOf<Response<Unit>>(Response.Success(Unit))
    val favUnfavState: State<Response<Unit>> = _favUnfavState

    lateinit var currentCharacter: Character

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(CHAR_ID)?.let { charId ->
                getCharacterDetails(charId.toInt())
            }
        }
    }

    private fun getCharacterDetails(charId: Int) {
        viewModelScope.launch {
            searchUseCases.getCharacterDetails(charId = charId).collect { response ->
                _state.value = response
            }
        }
    }

    fun checkIfCharacterIsFaved(charId: Int) {
        viewModelScope.launch {
            databaseUseCases.checkIfCharacterIsFaved.invoke(charId = charId).collect { response ->
                _isFavState.value = response
            }
        }
    }

    fun favCharacter() {
        viewModelScope.launch {
            databaseUseCases.favCharacter.invoke(character = currentCharacter.toDatabase())
                .collect { response ->
                    _favUnfavState.value = response

                    if (response is Response.Success)
                        _isFavState.value = Response.Success(true)
                }
        }
    }

    fun unfavCharacter() {
        viewModelScope.launch {
            databaseUseCases.unfavCharacter.invoke(character = currentCharacter.toDatabase())
                .collect { response ->
                    _favUnfavState.value = response

                    if (response is Response.Success)
                        _isFavState.value = Response.Success(false)
                }
        }
    }
}