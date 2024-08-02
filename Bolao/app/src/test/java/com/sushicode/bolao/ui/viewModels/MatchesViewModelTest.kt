package com.sushicode.bolao.ui.viewModels

import com.sushicode.bolao.BolaoTest
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import com.sushicode.bolao.mockMatch
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MatchesViewModelTest: BolaoTest() {

    private lateinit var viewModel: MatchesViewModel
    private lateinit var mockMatchesUseCase: MatchesUseCase

    @Before
    fun setup() {
        mockMatchesUseCase = mockk(relaxed = true)
        viewModel = MatchesViewModel(mockMatchesUseCase)
    }

    @Test
    fun `should call usecase getMatches()`() = runTest {
        viewModel.loadMatches()

        coVerify { mockMatchesUseCase.getMatches() }
    }

    @Test
    fun `should update matches state flow`() = runTest {
        coEvery { mockMatchesUseCase.getMatches() } returns listOf(mockMatch)

        viewModel.loadMatches()

        assertEquals("team_home: 4 X 6 :team_guest", viewModel.matchesStateFlow.value[0].toString())
    }

}