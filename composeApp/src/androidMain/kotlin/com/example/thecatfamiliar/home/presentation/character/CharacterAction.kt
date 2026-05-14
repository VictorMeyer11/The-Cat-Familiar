package com.example.thecatfamiliar.home.presentation.character

sealed interface CharacterAction {
    data class ChangeHP(val value: Int): CharacterAction
    data object OnSettingsClick: CharacterAction
}