package com.sushicode.bolao.domain.repositories

import com.sushicode.bolao.data.mappers.MatchesMapper
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.apis.MatchesApi
import com.sushicode.bolao.domain.entities.Match
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MatchesRepositoryImpl(
    private val matchesApi: MatchesApi,
    private val dispatcher: CoroutineDispatcher = IO
): MatchesRepository {

    override suspend fun getMatches(): List<Match> = withContext(dispatcher) {
        when (val matchesRequested = matchesApi.getMatches()) {
            is Result.Success -> { MatchesMapper.map(matchesRequested.result) }
            is Result.Error -> { emptyList() }
        }
    }
}