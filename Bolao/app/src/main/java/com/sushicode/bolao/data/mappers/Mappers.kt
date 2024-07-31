package com.sushicode.bolao.data.mappers

import com.sushicode.bolao.domain.entities.CupMatch
import com.sushicode.bolao.data.models.MatchResponse


object MatchesMapper: ResponseToEntityMapper<List<MatchResponse>, List<CupMatch>> {
    override fun map(response: List<MatchResponse>): List<CupMatch> {
        return response.map {
            CupMatch(
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