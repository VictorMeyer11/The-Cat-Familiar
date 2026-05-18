package com.example.thecatfamiliar.character.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecatfamiliar.core.Colors

@Composable
fun CharacterTopBar(
    characterName: String,
    onEditNameSave: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var newCharName by remember { mutableStateOf(characterName) }

    val isFocused by interactionSource.collectIsFocusedAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.Parchment)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Colors.ForestGreen.copy(alpha = 0.15f))
                .border(1.dp, Colors.BorderGrey, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                characterName.first().toString(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Colors.ForestGreen,
            )
        }
        Spacer(Modifier.width(12.dp))
        TextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            onValueChange = { newCharName = it },
            interactionSource = interactionSource,
            value = newCharName,
            textStyle = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Colors.TextDark
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Colors.TextDark,
                selectionColors = TextSelectionColors(
                    handleColor = Colors.ForestGreen,
                    backgroundColor = Colors.SereneGreen
                )
            )
        )
        IconButton(onClick = {
            if(isFocused) {
                onEditNameSave(newCharName)
                focusManager.clearFocus()
            }
            else {
                focusRequester.requestFocus()
            }
        }) {
            Icon(
                imageVector = if(isFocused) Icons.Default.Check else Icons.Default.Edit,
                contentDescription = "edit-char-name",
                tint = Colors.TextMuted
            )
        }
    }
}

@Preview
@Composable
fun CharacterTopBarPreview() {
    MaterialTheme {
        CharacterTopBar(
            characterName = "Evellyn Moonless",
            onEditNameSave = {}
        )
    }
}