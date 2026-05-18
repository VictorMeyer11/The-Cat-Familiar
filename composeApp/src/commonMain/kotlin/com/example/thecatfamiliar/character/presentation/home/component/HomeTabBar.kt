package com.example.thecatfamiliar.character.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecatfamiliar.core.Colors

@Composable
fun HomeTabBar(
    selectedIndex: Int,
    homeTabs: List<Pair<String, ImageVector>>,
    onSelect: (Int) -> Unit,
) {
    NavigationBar(containerColor = Colors.Parchment) {
        homeTabs.forEachIndexed { index, (label, icon) ->
            val selected = index == selectedIndex

            NavigationBarItem(
                selected = selected,
                onClick = { onSelect(index) },
                icon = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (selected) Colors.AccentPeach else Color.Transparent)
                            .padding(horizontal = 14.dp, vertical = 4.dp),
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = label,
                            tint = if (selected) Colors.ForestGreen else Colors.TextMuted,
                        )
                    }
                },
                label = {
                    Text(
                        text = label,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = if (selected) Colors.ForestGreen else Colors.TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Colors.ForestGreen,
                    selectedTextColor = Colors.ForestGreen,
                    unselectedIconColor = Colors.TextMuted,
                    unselectedTextColor = Colors.TextMuted,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}