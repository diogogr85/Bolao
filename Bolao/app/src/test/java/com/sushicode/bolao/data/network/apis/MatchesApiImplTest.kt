package com.sushicode.bolao.data.network.apis

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.HttpClientBuilder
import com.sushicode.bolao.mockEngine
import io.ktor.client.engine.mock.MockEngine
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MatchesApiImplTest {

    private lateinit var matchesApi: MatchesApi
    private lateinit var mockClient: HttpClientBuilder

    @Before
    fun setUp() {
        mockClient = HttpClientBuilder(mockEngine(), "mock.com")
        matchesApi = MatchesApiImpl(mockClient)
    }

    @Test
    fun `should return list of matches when getMatches is called`() = runBlocking {
        val result = matchesApi.getMatches()

        val resultItem = (result as Result.Success).result[0]
        val expected = Result.Success(mockMatchResponseList)

        assertEquals(result::class.java, expected::class.java)
        assertEquals(resultItem.teamHome, expected.result[0].teamHome)
    }

    private val mockMatchResponseList = listOf(MatchResponse(
        teamHome = "team_home NaN",
        teamGuest = "team_guest NaN",
        teamHomeScore = 41,
        teamGuestScore = 68,
        date = "date NaN",
        venue = "venue",
        round = "round",
        group = "group"
    ))
}