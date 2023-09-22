package com.example.poteauxrace.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.PlaceR
import com.example.poteauxrace.common.Pot
import com.example.poteauxrace.common.PotClaim
import com.example.poteauxrace.network.PotApi
import com.google.android.gms.location.LocationServices
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppStateUi())
    val uiState: StateFlow<AppStateUi> = _uiState.asStateFlow()

    fun updateTeam(teamId: Int) {
        _uiState.update { appStateUi -> appStateUi.copy(teamId = teamId, isTeamChosen = true) }
    }

    private fun updateGameStatus(status: Boolean = true, date: Int = 0) {
        _uiState.update { appStateUi: AppStateUi ->
            appStateUi.copy(
                isGameLaunched = status,
                startTime = date
            )
        }
    }

    fun updateClaimScreen(mode: Int = 0) {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(claimMode = mode) }
    }


    private fun resetClaimPanel() {
        _uiState.update { appStateUi: AppStateUi ->
            appStateUi.copy(
                potNumberField = "",
                isEntryPotWrong = false
            )
        }
    }


    private fun updatePotAndPlaces(poteaux: List<Pot>, places: List<PlaceR>) {
        _uiState.update { appStateUi -> appStateUi.copy(pot = poteaux) }
        places.forEach { place: PlaceR ->
            val olPl = _uiState.value.places[place.id]
            _uiState.value.places[place.id] = Place(
                id = place.id,
                name = olPl.name,
                pts = olPl.pts,
                hasBeenClaimed = true,
                whoClaimedit = place.teamId,
                location = olPl.location
            )
        }
    }

    fun updateRequest() {
        viewModelScope.launch {
            try {
                val update = PotApi.retrofitService.getUpdate()
                if (!update.uIsReady) {
                    updateGameStatus(status = false, date = 0)
                } else {
                    Log.d(null, "Ouais $update")
                    updateGameStatus(status = true, date = update.uStartTime)
                    updatePotAndPlaces(update.uPoteaux, update.uMonuments)
                }
            } catch (e: IOException) {
                Log.d(null, e.message ?: "ouais chef")
            } catch (e: Error) {
                Log.d(null, e.message ?: "ouais chef Error")
            }
        }
    }

    fun onConfirmPot(context: Activity): Boolean {
        if (_uiState.value.claimMode == 0) {
            val num = _uiState.value.potNumberField.toIntOrNull()
            if (num != null && num > 0) {
                resetClaimPanel()

                getCurrentLocationUser(context)

                val json =
                    Json.encodeToString(PotClaim("ClaimPoteau", listOf(_uiState.value.location.latitude, _uiState.value.location.longitude), 160000, 5, 2))
                // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
                val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
                CoroutineScope(Dispatchers.IO).launch {
                    // Do the POST request and get response
                    val response = PotApi.retrofitService.postClaim(requestBody)

                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            // Convert raw JSON to pretty JSON using GSON library
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            val prettyJson = gson.toJson(
                                JsonParser.parseString(
                                    response.body()
                                        ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                                )
                            )
                            Log.d("Pretty Printed JSON :", prettyJson)

                        } else {

                            Log.e("RETROFIT_ERROR", response.code().toString())

                        }
                    }
                }
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

    fun onPotChange(value: String): Unit {
        _uiState.update { appStateUi: AppStateUi -> appStateUi.copy(potNumberField = value) }
    }


    private fun updateCurrentLocation(loc: Location) {
        _uiState.update { it.copy(location = loc) }
    }

    fun getCurrentLocationUser(context: Activity) {

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val permissionCode = 101


        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != (PackageManager.PERMISSION_GRANTED) &&
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != (PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                context, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode
            )
            return
        }



        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            if (location != null) {
                updateCurrentLocation(location)
                Log.d("Localisation", "latitute ${location.latitude} et longitude ${location.longitude}")
            }
        }
    }
}
