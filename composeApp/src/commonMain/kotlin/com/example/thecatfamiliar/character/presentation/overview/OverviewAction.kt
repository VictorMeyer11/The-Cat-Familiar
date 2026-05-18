package com.example.thecatfamiliar.character.presentation.overview

sealed interface OverviewAction {
    data class ChangeHP(val value: Int): OverviewAction
}