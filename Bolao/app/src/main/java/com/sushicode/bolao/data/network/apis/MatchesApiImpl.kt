package com.sushicode.bolao.data.network.apis

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.HttpClientBuilder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.request

class MatchesApiImpl(private val httpClientBuilder: HttpClientBuilder): MatchesApi {

    private val GET_MATCHES_ENDPOINT = "https://66aad0e7636a4840d7c89baa.mockapi.io/sushicode/bolao/matches"

    override suspend fun getMatches(): Result<List<MatchResponse>> {
        val request = httpClientBuilder
            .build()
            .request(GET_MATCHES_ENDPOINT)
            .body<List<MatchResponse>>()

        return if (request.isNotEmpty()) Result.Success(request)
        else Result.Error(Exception("No matches found"))
    }
}