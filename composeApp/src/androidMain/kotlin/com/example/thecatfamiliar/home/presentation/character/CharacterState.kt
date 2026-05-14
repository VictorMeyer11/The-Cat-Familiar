package com.example.thecatfamiliar.home.presentation.character

data class CharacterState(
    val characterName: String,
    val currentHp: Int,
    val maxHp: Int,
    val tempHp: Int,
    val inventorySearchQuery: String,
) {
    companion object {
        fun initial(): CharacterState = CharacterState(
            characterName = "Caelum Ironheart",
            currentHp = 0,
            maxHp = 10,
            tempHp = 0,
            inventorySearchQuery = "",
        )
    }
}
