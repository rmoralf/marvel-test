package com.rmoralf.marveltest.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmoralf.marveltest.domain.model.Character
import com.rmoralf.marveltest.domain.model.Response
import com.rmoralf.marveltest.domain.usecases.database.DatabaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCases: DatabaseUseCases
) : ViewModel() {

    private val _state = mutableStateOf<Response<List<Character>>>(Response.Loading)
    val state: State<Response<List<Character>>> = _state

    fun getAllFavedCharacters() {
        viewModelScope.launch {
            useCases.getAllFavedCharacters.invoke().collect { response ->
                _state.value = response
            }
        }

    }
}