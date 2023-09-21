package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.Pot
import com.example.poteauxrace.common.Team
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(modifier :Modifier = Modifier,
              onButtonClicked: () -> Unit,
              onButtonObjClicked : () -> Unit,
              onButtonRefreshClicked : () -> Unit,
              poteaux : List<Pot>,
              monuments : List<Place>,
              teams : List<Team>,
              ) {
    Scaffold(floatingActionButton = {ClaimButton(modifier = Modifier,
        onButtonClicked = onButtonClicked,
        onButtonClicked2 = onButtonObjClicked,
        onButtonClicked3 = onButtonRefreshClicked) },
        ) { paddingValues ->
        val lyon = LatLng(45.74, 4.84)
        val cameraPositionState: CameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(lyon, 12.4f)
        }
        Box(modifier = Modifier.padding(paddingValues)) {
            GoogleMap(cameraPositionState = cameraPositionState) {
                poteaux.forEach{pot : Pot ->
                    Marker(
                        state = MarkerState(position = LatLng(pot.pLocation[0],pot.pLocation[1])),
                        title = "Positionned by team ${teams[pot.pTeam].name}}",
                        icon = BitmapDescriptorFactory.defaultMarker(teams[pot.pTeam].hue)
                    )
                }
            }
        }
    }
}
@Composable
fun ClaimButton(modifier: Modifier= Modifier, onButtonClicked: () -> Unit, onButtonClicked2: () -> Unit, onButtonClicked3: () -> Unit) {
    Column() {
        FloatingActionButton(onClick = onButtonClicked) {
            Icon(Icons.Rounded.Add, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(12.dp))
        FloatingActionButton(onClick = onButtonClicked2) {
            Icon(Icons.Rounded.List, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(12.dp))
        FloatingActionButton(onClick = onButtonClicked3) {
            Icon(Icons.Rounded.Refresh, contentDescription = null)
        }
    }
}