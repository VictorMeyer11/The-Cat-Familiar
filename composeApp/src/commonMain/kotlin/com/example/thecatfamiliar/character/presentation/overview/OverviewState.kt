package com.example.thecatfamiliar.character.presentation.overview

data class OverviewState(
    val characterName: String,
    val currentHp: Int,
    val maxHp: Int,
    val tempHp: Int
) {
    companion object {
        fun initial(): OverviewState = OverviewState(
            characterName = "Caelum Ironheart",
            currentHp = 0,
            maxHp = 10,
            tempHp = 0
        )
    }
}
