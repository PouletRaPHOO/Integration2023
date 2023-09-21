package com.example.poteauxrace.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.PlaceR
import com.example.poteauxrace.common.Pot
import com.example.poteauxrace.common.UpdateResponse
import com.example.poteauxrace.network.PotApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppStateUi())
    val uiState: StateFlow<AppStateUi> = _uiState.asStateFlow()

    fun updateTeam(teamId: Int) {
        _uiState.update { appStateUi -> appStateUi.copy(teamId = teamId, isTeamChosen = true)  }
    }
    fun updateGameStatus(status : Boolean = true, date : Int = 0) {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(isGameLaunched = status, startTime = date) }
    }

    fun updateClaimScreen(mode : Int = 0) {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(claimMode = mode ) }
    }


    private fun resetClaimPanel() {
        _uiState.update { appStateUi : AppStateUi-> appStateUi.copy(potNumberField = "", isEntryPotWrong = false) }
    }


    private fun updatePotAndPlaces(poteaux : List<Pot>, places : List<PlaceR>) {
        _uiState.update { appStateUi -> appStateUi.copy(pot = poteaux)}
        places.forEach { place:PlaceR -> val olPl = _uiState.value.places[place.id]
            _uiState.value.places[place.id] =Place(id=place.id, name = olPl.name, pts = olPl.pts, hasBeenClaimed = true, whoClaimedit = place.teamId ) }
    }

    fun updateRequest() {
        viewModelScope.launch {
            try  {
                val update = PotApi.retrofitService.getUpdate()
                if (!update.uIsReady){ updateGameStatus(status = false, date = 0)}
                else {
                    updateGameStatus(status = true, date = update.uStartTime)
                    updatePotAndPlaces(update.uPoteaux, update.uMonuments)
                }
            } catch(e : IOException) {
                Log.d(null, e.message?:"ouais chef")
            } catch (e : Error){
                Log.d(null, e.message?:"ouais chef Error")
            }
        }
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