package com.example.thecatfamiliar.character.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState.initial())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    val homeTabs: List<Pair<String, ImageVector>> = listOf(
        "Character" to Icons.Default.Person,
        "Inventory" to Icons.AutoMirrored.Filled.List,
        "Spells" to Icons.Filled.Menu,
        "Log" to Icons.Default.Create
    )

    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.ChangeToTab -> {
                _state.update { it.copy(selectedTab = action.tabIndex) }
            }
            is HomeAction.OnEditNameSave -> _state.update {
                it.copy(characterName = action.newName)
            }
        }
    }
}