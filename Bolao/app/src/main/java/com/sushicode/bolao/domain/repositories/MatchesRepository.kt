package com.sushicode.bolao.domain.repositories

import com.sushicode.bolao.domain.entities.Match

interface MatchesRepository {
    suspend fun getMatches(): List<Match>
}