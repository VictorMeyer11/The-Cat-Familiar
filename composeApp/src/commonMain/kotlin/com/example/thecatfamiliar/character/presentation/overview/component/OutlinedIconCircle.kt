package com.example.thecatfamiliar.character.presentation.overview.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thecatfamiliar.core.Colors

@Composable
fun OutlinedIconCircle(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Colors.BorderGrey, CircleShape),
    ) {
        content()
    }
}