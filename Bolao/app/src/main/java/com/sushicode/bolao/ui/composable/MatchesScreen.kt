package com.sushicode.bolao.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sushicode.bolao.domain.entities.Match
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = koinViewModel()
) {
    val matchesList by viewModel.matchesStateFlow.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(mockMatches) { match ->
            MatchItem(match = match)
        }
    }
}

@Composable
fun MatchItem(match: Match) {
    Box(
        Modifier
            .padding(0.dp, 15.dp, 0.dp, 15.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = match.teamHome)
        Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${match.teamHomeScore} X ${match.teamGuestScore}")
        Spacer(modifier = Modifier.width(16.dp))
            Text(text = match.teamGuest)
        }
    }
}

private val mockMatches = listOf(
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ),
    Match(
        teamHome = "team_home",
        teamGuest = "team_guest",
        teamHomeScore = 4,
        teamGuestScore = 6,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    )
)