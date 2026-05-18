package com.example.thecatfamiliar.character.presentation.home


sealed interface HomeAction {
    data class ChangeToTab(val tabIndex: Int): HomeAction
    data class OnEditNameSave(val newName: String): HomeAction
}