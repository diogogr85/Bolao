package com.sushicode.bolao

import com.sushicode.bolao.data.network.HttpClientBuilder
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HttpClientBuilderTest {

    private lateinit var mockEngine: MockEngine

    @Before
    fun setUp() {
        mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("test"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
    }

    @After
    fun dismiss() {

    }

    @Test
    fun `config HttpClient`() = runBlocking {
        val httpClient = HttpClientBuilder(engine = mockEngine, host = "host-client.com")

        val request: HttpResponse = httpClient.build().get("/config")
        val response = request.body<String>()


        assertEquals("application/json", request.headers["Content-Type"])
        assertEquals("https://host-client.com/config", request.request.url.toString())
        assertEquals("test", response)
    }

}