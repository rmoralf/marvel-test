package com.rmoralf.marveltest.presentation.details

import androidx.lifecycle.ViewModel
import com.rmoralf.marveltest.domain.usecases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: SearchUseCases
) : ViewModel() {


    init {
//        getCharacterList()
    }

/*
    fun getCharacterList() {
        viewModelScope.launch {
            useCases.getAllCharacters().collect { response ->
                _state.value = response
            }
        }

    }
*/


}