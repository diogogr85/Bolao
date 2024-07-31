package com.sushicode.bolao.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol

class HttpClientBuilder(
    private val engine: HttpClientEngine = Android.create(),
    private val urlProtocol: URLProtocol = URLProtocol.HTTPS,
    private val host: String
) {

    fun build(): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true

            defaultRequest {
                url {
                    protocol = urlProtocol
                    host = this@HttpClientBuilder.host
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(Logging)
        }
    }

}