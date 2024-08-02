package com.sushicode.bolao.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushicode.bolao.domain.entities.Match
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchesViewModel(private val matchesUseCase: MatchesUseCase): ViewModel() {

    private val _matchesStateFlow = MutableStateFlow<MutableList<Match>>(mutableListOf())
    val matchesStateFlow: StateFlow<List<Match>> = _matchesStateFlow

    fun loadMatches() {
        viewModelScope.launch {
            val matches = matchesUseCase.getMatches()
            _matchesStateFlow.value = matches.toMutableList()
        }
    }

}