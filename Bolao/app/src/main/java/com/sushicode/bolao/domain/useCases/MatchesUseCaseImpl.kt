package com.sushicode.bolao.domain.useCases

import com.sushicode.bolao.domain.entities.Match
import com.sushicode.bolao.domain.repositories.MatchesRepository

class MatchesUseCaseImpl(private val matchesRepository: MatchesRepository): MatchesUseCase {
    override suspend fun getMatches(): List<Match> = matchesRepository.getMatches()
}