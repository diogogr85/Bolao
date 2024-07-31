package com.sushicode.bolao.domain.repositories

import com.sushicode.bolao.data.mappers.MatchesMapper
import com.sushicode.bolao.domain.entities.CupMatch
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.apis.MatchesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MatchesRepositoryImpl(
    private val matchesApi: MatchesApi,
    private val dispatcher: CoroutineDispatcher
): MatchesRepository {

    override suspend fun getMatches(): List<CupMatch> = withContext(dispatcher) {
        when (val matchesRequested = matchesApi.getMatches()) {
            is Result.Success -> { MatchesMapper.map(matchesRequested.result) }
            is Result.Error -> { emptyList() }
        }

    }


}