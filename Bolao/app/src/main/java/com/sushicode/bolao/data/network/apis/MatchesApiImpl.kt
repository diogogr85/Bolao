package com.sushicode.bolao.data.network.apis

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.data.models.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MatchesApiImpl(private val httpClient: HttpClient): MatchesApi {

    private val GET_MATCHES_ENDPOINT = "/matches"

    override suspend fun getMatches(): Result<List<MatchResponse>> {
        val request = httpClient.get(GET_MATCHES_ENDPOINT).body<List<MatchResponse>>()

        return if (request.isNotEmpty()) Result.Success(request)
        else Result.Error(Exception("No matches found"))
    }
}