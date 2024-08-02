package com.sushicode.bolao.data.network.apis

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.HttpClientBuilder
import io.ktor.client.call.body
import io.ktor.client.request.request

class MatchesApiImpl(private val httpClientBuilder: HttpClientBuilder): MatchesApi {

    private val GET_MATCHES_ENDPOINT = "/matches"

    override suspend fun getMatches(): Result<List<MatchResponse>> {
        val request = httpClientBuilder
            .build()
            .request(GET_MATCHES_ENDPOINT)
            .body<List<MatchResponse>>()

        return if (request.isNotEmpty()) Result.Success(request)
        else Result.Error(Exception("No matches found"))
    }
}