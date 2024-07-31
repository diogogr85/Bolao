package com.sushicode.bolao.domain.repositories

import com.sushicode.bolao.domain.entities.CupMatch

interface MatchesRepository {
    suspend fun getMatches(): List<CupMatch>
}