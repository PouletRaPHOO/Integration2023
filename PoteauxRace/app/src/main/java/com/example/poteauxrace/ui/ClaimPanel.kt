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
    ) {
    Column(modifier = Modifier.fillMaxSize()){
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

    }
}