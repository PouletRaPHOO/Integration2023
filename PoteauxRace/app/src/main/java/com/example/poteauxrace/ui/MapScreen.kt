package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Box
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(modifier :Modifier = Modifier, onButtonClicked: () -> Unit, onButtonObjClicked : () -> Unit, onButtonRefreshClicked : () -> Unit) {
    Scaffold(floatingActionButton = {ClaimButton(modifier = Modifier,
        onButtonClicked = onButtonClicked,
        onButtonClicked2 = onButtonObjClicked,
        onButtonClicked3 = onButtonRefreshClicked) },
        ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(text = "WIP")
        }
    }
}
@Composable
fun ClaimButton(modifier: Modifier= Modifier, onButtonClicked: () -> Unit, onButtonClicked2: () -> Unit, onButtonClicked3: () -> Unit) {
    FloatingActionButton(onClick = onButtonClicked) {
        Icon(Icons.Rounded.Add, contentDescription = null)
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = onButtonClicked2) {
        Icon(Icons.Rounded.List, contentDescription = null)
    }
    Spacer(modifier = Modifier.height(12.dp))
    FloatingActionButton(onClick = onButtonClicked) {
        Icon(Icons.Rounded.Refresh, contentDescription = null)
    }
}