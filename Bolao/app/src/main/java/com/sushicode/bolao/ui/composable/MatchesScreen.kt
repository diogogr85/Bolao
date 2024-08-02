package com.sushicode.bolao.ui.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sushicode.bolao.domain.entities.Match
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = koinViewModel()
) {
    val matchesList by viewModel.matchesStateFlow.collectAsStateWithLifecycle()

    LazyColumn {
        items(matchesList) { match ->
                Text(text = match.toString())
            }
    }
}

