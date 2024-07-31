package com.sushicode.bolao.domain.useCases

import com.sushicode.bolao.domain.entities.CupMatch

interface MatchesUseCase {
    suspend fun getMatches(): List<CupMatch>
}