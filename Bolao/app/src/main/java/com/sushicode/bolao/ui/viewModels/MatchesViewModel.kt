package com.sushicode.bolao.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushicode.bolao.domain.entities.CupMatch
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import kotlinx.coroutines.launch

class MatchesViewModel(private val matchesUseCase: MatchesUseCase): ViewModel() {

    private val _matchesLiveData = MutableLiveData<List<CupMatch>>()
    val matchesLiveData: LiveData<List<CupMatch>> = _matchesLiveData

    fun loadMatches() {
        viewModelScope.launch {
            val matches = matchesUseCase.getMatches()
            _matchesLiveData.value = matches
        }
    }

}