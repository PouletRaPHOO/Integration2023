package com.example.poteauxrace.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.Pot
import com.example.poteauxrace.common.Team
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_CYAN
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(modifier :Modifier = Modifier,
              onButtonClicked: () -> Unit,
              onButtonObjClicked : () -> Unit,
              onButtonRefreshClicked : () -> Unit,
              poteaux : List<Pot>,
              monuments : List<Place>,
              teams : List<Team>,
              startTime: Long,
              teamId:Int,
              ) {
    Scaffold(floatingActionButton = {ClaimButton(modifier = Modifier,
        onButtonClicked = onButtonClicked,
        onButtonClicked2 = onButtonObjClicked,
        onButtonClicked3 = onButtonRefreshClicked) },
        topBar = {Topb(startTime = startTime, team = teams[teamId].name)}
        ) { paddingValues ->
        val lyon = LatLng(45.74, 4.84)
        val cameraPositionState: CameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(lyon, 12.4f)
        }
        Box(modifier = Modifier.padding(paddingValues)) {
            GoogleMap(cameraPositionState = cameraPositionState) {
                Log.d(null, "Longueur ${poteaux.size}")
                for (pot in poteaux) {

                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                pot.pLocation[0],
                                pot.pLocation[1]
                            )
                        ),
                        title = "Poteau",
                        snippet = "",
                        icon = BitmapDescriptorFactory.defaultMarker(teams[pot.pTeam].hue)
                    )
                }

                for (mon in monuments) {

                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                mon.location[0],
                                mon.location[1]
                            )
                        ),
                        title = mon.name,
                        snippet = mon.description,
                        icon = BitmapDescriptorFactory.defaultMarker(36f)
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


@Composable
fun Topb(modifier: Modifier = Modifier, startTime : Long, team : String) {
    Box(modifier = modifier
        .height(45.dp)
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.primaryContainer))
         {

        val now = Instant.now().toEpochMilli()/1000
        val timer = startTime + 2*60*60*1000 - now
        val minutes = timer/1000/60 - (timer/1000/60/60)*60
        Row (modifier= Modifier.fillMaxSize()){
            Text(text = "0${timer/60/60/1000}:$minutes", fontSize = 36.sp, modifier = Modifier.padding(start = 12.dp))
            Text(text = "Equipe $team", modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp, bottom = 4.dp, top = 4.dp), textAlign = TextAlign.End, fontSize = 24.sp)
        }



    }
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}