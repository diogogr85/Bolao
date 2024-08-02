package com.sushicode.bolao.data.mappers

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.domain.entities.Match


object MatchesMapper: ResponseToEntityMapper<List<MatchResponse>, List<Match>> {
    override fun map(response: List<MatchResponse>): List<Match> {
        return response.map {
            Match(
                teamHome = it.teamHome,
                teamGuest = it.teamGuest,
                teamHomeScore = it.teamHomeScore,
                teamGuestScore = it.teamGuestScore,
                date = it.date,
                venue = it.venue,
                group = it.group,
                round = it.round
            )
        }
    }

}