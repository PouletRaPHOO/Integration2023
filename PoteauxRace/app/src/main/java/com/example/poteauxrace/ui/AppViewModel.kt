package com.example.poteauxrace.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppStateUi())
    val uiState: StateFlow<AppStateUi> = _uiState.asStateFlow()

    fun updateTeam(teamId: Int) {
        _uiState.update { appStateUi -> appStateUi.copy(teamId = teamId, isTeamChosen = true)  }
    }
    fun updateGameStatus(status : Boolean = true) {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(isGameLaunched = status ) }
    }

    fun updateClaimScreen(mode : Int = 0) {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(claimMode = mode ) }
    }
}