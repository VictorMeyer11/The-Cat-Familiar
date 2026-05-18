package com.example.thecatfamiliar.character.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thecatfamiliar.character.presentation.home.component.HomeTabBar
import com.example.thecatfamiliar.character.presentation.inventory.InventoryScreen
import com.example.thecatfamiliar.character.presentation.overview.OverviewScreen
import com.example.thecatfamiliar.character.presentation.home.component.CharacterTopBar
import com.example.thecatfamiliar.core.Colors
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        onAction = viewModel::onAction,
        homeTabs = viewModel.homeTabs
    )
}

@Composable
fun HomeScreenContent(
    homeTabs: List<Pair<String, ImageVector>>,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Colors.Parchment,
        topBar = {
            CharacterTopBar(
                characterName = state.characterName,
                onEditNameSave = { onAction(HomeAction.OnEditNameSave(it)) } ,
            )
        },
        bottomBar = {
            HomeTabBar(
                selectedIndex = state.selectedTab,
                homeTabs = homeTabs,
                onSelect = { onAction(HomeAction.ChangeToTab(it)) },
            )
        },
    ) { innerPadding ->
        when (state.selectedTab) {
            0 -> OverviewScreen(Modifier.padding(innerPadding))
            1 -> InventoryScreen()
            else -> HomeTabPlaceholder(
                modifier = Modifier.padding(innerPadding),
                title = homeTabs[state.selectedTab].first,
            )
        }
    }
}

@Composable
private fun HomeTabPlaceholder(modifier: Modifier, title: String) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.Parchment),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            color = Colors.TextMuted,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
