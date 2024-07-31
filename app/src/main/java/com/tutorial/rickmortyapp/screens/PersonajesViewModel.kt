package com.tutorial.rickmortyapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorial.rickmortyapp.model.Personaje
import com.tutorial.rickmortyapp.repository.PersonajesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonajesViewModel @Inject constructor(
    private val repo: PersonajesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Personaje>())
    val state: StateFlow<List<Personaje>> get() = _state
    init {
        viewModelScope.launch {
            _state.value = repo.getPersonajes().results
        }
    }
}