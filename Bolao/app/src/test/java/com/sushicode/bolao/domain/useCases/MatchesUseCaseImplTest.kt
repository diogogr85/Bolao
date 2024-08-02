package com.sushicode.bolao.domain.useCases

import com.sushicode.bolao.BolaoTest
import com.sushicode.bolao.domain.repositories.MatchesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MatchesUseCaseImplTest: BolaoTest() {

    private lateinit var matchesUseCase: MatchesUseCase
    private lateinit var mockRepository: MatchesRepository

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        matchesUseCase = MatchesUseCaseImpl(mockRepository)
    }

    @Test
    fun `should call repository getMatches() when usecase getMatches() is called`() = runTest {
        matchesUseCase.getMatches()

        coVerify { mockRepository.getMatches() }
    }


}