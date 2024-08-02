package com.sushicode.bolao.data.mappers

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.domain.entities.Match
import io.ktor.util.reflect.instanceOf
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class MatchesMapperTest {

    @Test
    fun `should map MatchResponse to Match`() {
        val matchResponse = MatchResponse(
            teamHome = "team_home",
            teamGuest = "team_guest",
            teamHomeScore = 4,
            teamGuestScore = 6,
            date = "date NaN",
            venue = "venue",
            round = "round",
            group = "group"
        )

        val matchMapped = MatchesMapper.map(listOf(matchResponse))

        assertInstanceOf(Match::class.java, matchMapped[0])
    }
}