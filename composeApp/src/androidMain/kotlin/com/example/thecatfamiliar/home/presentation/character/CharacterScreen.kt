package com.example.thecatfamiliar.home.presentation.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thecatfamiliar.core.CharacterSheetColors

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterScreenContent(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun CharacterScreenContent(
    modifier: Modifier,
    state: CharacterState,
    onAction: (CharacterAction) -> Unit
) {
    Column(modifier.fillMaxSize()) {
        CharacterTopBar(
            characterName = state.characterName,
            onSettingsClick = { onAction(CharacterAction.OnSettingsClick) } ,
        )
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            HealthPointsCard(
                currentHp = state.currentHp,
                maxHp = state.maxHp,
                tempHp = state.tempHp,
                onDecrement = onHpDecrement,
                onIncrement = onHpIncrement,
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = state.inventorySearchQuery,
                onValueChange = onInventorySearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Search inventory...",
                        color = CharacterSheetColors.TextMuted,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = CharacterSheetColors.TextMuted)
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.65f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.65f),
                    focusedBorderColor = CharacterSheetColors.BorderGrey,
                    unfocusedBorderColor = CharacterSheetColors.BorderGrey.copy(alpha = 0.6f),
                    cursorColor = CharacterSheetColors.ForestGreen,
                    focusedTextColor = CharacterSheetColors.TextDark,
                    unfocusedTextColor = CharacterSheetColors.TextDark,
                ),
            )
        }
    }
}

@Composable
private fun CharacterTopBar(
    characterName: String,
    onSettingsClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CharacterSheetColors.Parchment)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(CharacterSheetColors.ForestGreen.copy(alpha = 0.15f))
                .border(1.dp, CharacterSheetColors.BorderGrey, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                characterName.first().toString(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = CharacterSheetColors.ForestGreen,
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            characterName,
            modifier = Modifier.weight(1f),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = CharacterSheetColors.TextDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        IconButton(onClick = onSettingsClick) {
            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = CharacterSheetColors.TextMuted)
        }
    }
}

@Composable
private fun HealthPointsCard(
    currentHp: Int,
    maxHp: Int,
    tempHp: Int,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
) {
    val ratio = (currentHp.toFloat() / maxHp.toFloat()).coerceIn(0f, 1f)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CharacterSheetColors.CardBeige),
        border = BorderStroke(1.dp, CharacterSheetColors.BorderGrey),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "HEALTH POINTS",
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.8.sp,
                color = CharacterSheetColors.TextMuted,
            )
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedIconCircle(onClick = onDecrement) {
                        Text(
                            "−",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = CharacterSheetColors.ForestGreen,
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        currentHp.toString(),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = CharacterSheetColors.ForestGreen,
                    )
                    Text(
                        " / ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 28.sp,
                        color = CharacterSheetColors.HpTrack,
                    )
                    Text(
                        maxHp.toString(),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        color = CharacterSheetColors.TextMuted,
                    )
                    Spacer(Modifier.width(12.dp))
                    OutlinedIconCircle(onClick = onIncrement) {
                        Icon(Icons.Default.Add, contentDescription = "Increase HP", tint = CharacterSheetColors.ForestGreen)
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CharacterSheetColors.ChipGrey)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                ) {
                    Text(
                        "+$tempHp Temp",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = CharacterSheetColors.TextMuted,
                    )
                }
            }
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CharacterSheetColors.HpTrack),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(ratio)
                        .background(CharacterSheetColors.ForestGreen),
                )
            }
        }
    }
}

@Composable
private fun OutlinedIconCircle(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, CharacterSheetColors.BorderGrey, CircleShape),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterScreenPreview() {
    CharacterScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = CharacterState.initial(),
        onSettingsClick = {},
        onHpDecrement = {},
        onHpIncrement = {},
        onInventorySearchQueryChange = {},
    )
}
