package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(modifier :Modifier = Modifier, onButtonClicked: () -> Unit) {
    Scaffold(floatingActionButton = {ClaimButton(modifier = Modifier, onButtonClicked = onButtonClicked)} ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(text = "WIP")
        }
    }
}
@Composable
fun ClaimButton(modifier: Modifier= Modifier, onButtonClicked: () -> Unit) {
    Button(onClick = onButtonClicked) {
        //Icon(painter = null, contentDescription = null)

    }
}