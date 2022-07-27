package com.rmoralf.marveltest.presentation.search

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rmoralf.marveltest.domain.usecases.search.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: SearchUseCases
) : ViewModel() {

    var lastSearch = ""

    private val searchQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    val characters = searchQuery.flatMapLatest { nameStartsWith ->
        useCases.searchCharacters(nameStartsWith).cachedIn(viewModelScope)
    }

    fun searchCharacter(searchTxt: String) {
        lastSearch = searchTxt
        searchQuery.value = searchTxt
    }

}