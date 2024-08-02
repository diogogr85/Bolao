package com.sushicode.bolao.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientBuilder(
    private val engine: HttpClientEngine = Android.create(),
    private val baseUrl: String,
    private val urlProtocol: URLProtocol = URLProtocol.HTTPS
) {

    fun build(): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true

            defaultRequest {
                url {
                    protocol = urlProtocol
                    host = baseUrl
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging)
        }
    }

}