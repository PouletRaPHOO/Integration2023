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

    fun gamelaunchRequest() : Boolean{
        /* Quand je saurais quoi faire je metttrais un tru ici"*/
        return true
    }

    private fun resetClaimPanel() {
        _uiState.update { appStateUi : AppStateUi-> appStateUi.copy(potNumberField = "", isEntryPotWrong = false) }
    }

    fun claimRequest() {

    }

    fun onConfirmPot() : Boolean{
        if (_uiState.value.claimMode == 0) {
            val num = _uiState.value.potNumberField.toIntOrNull()
            if (num != null && num > 0) {
                resetClaimPanel()
                return true
            }
            _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(isEntryPotWrong = true) }
            return false
        }
        return true
    }

    fun onCancelPot() {
        resetClaimPanel()
    }

    fun onPotChange(value:String) : Unit {
        _uiState.update { appStateUi : AppStateUi-> appStateUi.copy(potNumberField = value) }
    }
}