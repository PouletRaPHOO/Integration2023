package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimPanel(
    modifier : Modifier = Modifier,
    onNextButtonClicked : () -> Unit,
    onCancelButtonClicked: () -> Unit,
    viewModel: AppViewModel,
    mode : Int = 0
    ) {
    Column(modifier = Modifier.fillMaxSize()){
        Row (modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier.weight(0.5f), onClick = {viewModel.updateClaimScreen(0)}) {
                Text("Poteaux")
            }
            Button(modifier = Modifier.weight(0.5f), onClick = {viewModel.updateClaimScreen(1)}) {
                Text("Monuments")
            }
        }
        if (mode == 0) {
            Text(text= "Nombre de Poteaux")
            TextField(value = "Nombre de Poteaux", onValueChange = {})
            Row( modifier = Modifier.fillMaxWidth()){
                Button(onClick = onCancelButtonClicked) {
                    Text(text = "Cancel")
                }
                Button(onClick = onNextButtonClicked) {
                    Text(text = "Confirm")
                }
            }

        } else {
            Text(text= "Ouais la jsp pas encore mais y'aura un truc je crois")
        }

    }
}