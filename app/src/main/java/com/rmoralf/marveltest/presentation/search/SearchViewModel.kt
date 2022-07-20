package com.rmoralf.marveltest.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.rmoralf.marveltest.domain.model.CharacterDataWrapper
import com.rmoralf.marveltest.domain.model.Response
import com.rmoralf.marveltest.domain.usecases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: SearchUseCases
) : ViewModel() {

    val characters = useCases.getAllCharacters().cachedIn(viewModelScope)

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