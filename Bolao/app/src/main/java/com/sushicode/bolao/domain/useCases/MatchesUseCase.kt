package com.sushicode.bolao.domain.useCases

import com.sushicode.bolao.domain.entities.Match

interface MatchesUseCase {
    suspend fun getMatches(): List<Match>
}