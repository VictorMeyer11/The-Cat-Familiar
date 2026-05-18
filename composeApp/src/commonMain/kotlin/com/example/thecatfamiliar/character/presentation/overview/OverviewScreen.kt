package com.example.thecatfamiliar.character.presentation.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thecatfamiliar.character.presentation.overview.component.HealthPointsCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    OverviewScreenContent(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun OverviewScreenContent(
    modifier: Modifier,
    state: OverviewState,
    onAction: (OverviewAction) -> Unit
) {
    Column(modifier.fillMaxSize()) {
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
                onHpDecrement = { onAction(OverviewAction.ChangeHP(-1)) },
                onHpIncrement = { onAction(OverviewAction.ChangeHP(1)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OverviewScreenPreview() {
    OverviewScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = OverviewState.initial(),
        onAction = {}
    )
}
