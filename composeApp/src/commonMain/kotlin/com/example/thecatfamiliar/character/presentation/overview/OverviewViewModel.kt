package com.example.thecatfamiliar.character.presentation.overview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OverviewViewModel : ViewModel() {

    private val _state = MutableStateFlow(OverviewState.initial())
    val state: StateFlow<OverviewState> = _state.asStateFlow()

    fun onAction(action: OverviewAction) {
        when(action) {
            is OverviewAction.ChangeHP -> _state.update {
                it.copy(currentHp = it.currentHp + action.value)
            }
        }
    }
}
