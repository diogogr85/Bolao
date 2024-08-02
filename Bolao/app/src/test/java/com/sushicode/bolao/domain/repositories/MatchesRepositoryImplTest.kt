package com.sushicode.bolao.domain.repositories

import com.sushicode.bolao.BolaoTest
import com.sushicode.bolao.data.models.Result
import com.sushicode.bolao.data.network.apis.MatchesApiImpl
import com.sushicode.bolao.mockMatchResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MatchesRepositoryImplTest: BolaoTest() {

    private lateinit var mockMatchesApi: MatchesApiImpl
    private lateinit var matchesRepository: MatchesRepository

    @Before
    fun setUp() {
        mockMatchesApi = mockk(relaxed = true)
        matchesRepository = MatchesRepositoryImpl(mockMatchesApi, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `should return Matches list when result success`() = runTest {
        coEvery { mockMatchesApi.getMatches() } returns Result.Success(listOf(mockMatchResponse))

        val result = matchesRepository.getMatches()

        assertTrue(result.isNotEmpty())
        assertEquals(result[0].teamHome, "team_home")
    }

    @Test
    fun `should return empty list when result error`() = runTest {
        coEvery { mockMatchesApi.getMatches() } returns Result.Error(Exception())

        val result = matchesRepository.getMatches()

        assertTrue(result.isEmpty())
    }



}