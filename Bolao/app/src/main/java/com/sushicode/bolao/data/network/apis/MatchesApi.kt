package com.sushicode.bolao.data.network.apis

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.data.models.Result

interface MatchesApi {
    suspend fun getMatches(): Result<List<MatchResponse>>
}