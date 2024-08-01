package com.sushicode.bolao.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushicode.bolao.domain.entities.CupMatch
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchesViewModel(private val matchesUseCase: MatchesUseCase): ViewModel() {

    private val _matchesStateFlow = MutableStateFlow<List<CupMatch>>(listOf())
    val matchesStateFlow: StateFlow<List<CupMatch>> = _matchesStateFlow

    fun loadMatches() {
        viewModelScope.launch {
            val matches = matchesUseCase.getMatches()
            _matchesStateFlow.value = matches
        }
    }

}