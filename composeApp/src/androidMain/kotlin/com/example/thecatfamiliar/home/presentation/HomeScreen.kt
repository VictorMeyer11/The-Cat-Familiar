package com.example.thecatfamiliar.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecatfamiliar.core.CharacterSheetColors
import com.example.thecatfamiliar.home.presentation.character.CharacterScreen

private val homeTabs: List<Pair<String, ImageVector>> = listOf(
    "Character" to Icons.Default.Person,
    "Inventory" to Icons.Default.List,
    "Spells" to Icons.Default.Star,
    "Journal" to Icons.Default.Create,
)

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CharacterSheetColors.Parchment,
        bottomBar = {
            HomeTabBar(
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
            )
        },
    ) { innerPadding ->
        when (selectedTab) {
            0 -> CharacterScreen(Modifier.padding(innerPadding))
            else -> HomeTabPlaceholder(
                modifier = Modifier.padding(innerPadding),
                title = homeTabs[selectedTab].first,
            )
        }
    }
}

@Composable
private fun HomeTabPlaceholder(modifier: Modifier, title: String) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CharacterSheetColors.Parchment),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            color = CharacterSheetColors.TextMuted,
        )
    }
}

@Composable
private fun HomeTabBar(
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    NavigationBar(
        containerColor = CharacterSheetColors.Parchment,
        tonalElevation = 0.dp,
    ) {
        homeTabs.forEachIndexed { index, (label, icon) ->
            val selected = index == selectedIndex

            NavigationBarItem(
                selected = selected,
                onClick = { onSelect(index) },
                icon = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (selected) CharacterSheetColors.AccentPeach else Color.Transparent)
                            .padding(horizontal = 14.dp, vertical = 4.dp),
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = label,
                            tint = if (selected) CharacterSheetColors.ForestGreen else CharacterSheetColors.TextMuted,
                        )
                    }
                },
                label = {
                    Text(
                        label,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = if (selected) CharacterSheetColors.ForestGreen else CharacterSheetColors.TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CharacterSheetColors.ForestGreen,
                    selectedTextColor = CharacterSheetColors.ForestGreen,
                    unselectedIconColor = CharacterSheetColors.TextMuted,
                    unselectedTextColor = CharacterSheetColors.TextMuted,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
