package com.sushicode.bolao.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientBuilder(private val engine: HttpClientEngine = Android.create()) {

    fun build(): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true

//            defaultRequest {
//                url {
//                    protocol = urlProtocol
//                    host = this@HttpClientBuilder.host
//                }
//                header(HttpHeaders.ContentType, ContentType.Application.Json)
//            }
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