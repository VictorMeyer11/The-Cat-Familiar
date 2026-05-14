package com.example.thecatfamiliar.home.presentation.character

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharacterViewModel : ViewModel() {

    private val _state = MutableStateFlow(CharacterState.initial())
    val state: StateFlow<CharacterState> = _state.asStateFlow()

    fun onAction(action: CharacterAction) {
        when(action) {
            is CharacterAction.ChangeHP -> {
                _state.update { it. }
            }
            CharacterAction.OnSettingsClick -> TODO()
        }
    }
}
