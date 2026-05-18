package com.example.thecatfamiliar.character.presentation.home

data class HomeState(
    val selectedTab: Int,
    val characterName: String
) {
    companion object {
        fun initial(): HomeState = HomeState(
            selectedTab = 0,
            characterName = "Dio"
        )
    }
}
