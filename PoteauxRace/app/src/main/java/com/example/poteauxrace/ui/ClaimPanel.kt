package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimPanel(
    modifier : Modifier = Modifier,
    onNextButtonClicked : () -> Unit,
    onCancelButtonClicked: () -> Unit,
    viewModel: AppViewModel,
    mode : Int = 0,
    potFieldValue : String,
    onPotChange : (String) -> Unit,

    ) {
    Column(modifier = Modifier.fillMaxSize()){
        Row (modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(0.5f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = { viewModel.updateClaimScreen(0) }) {
                    Text("Poteaux")
                }
                if (mode == 1) {
                    Divider(thickness = 4.dp)
                }
            }
            Column(modifier = Modifier.weight(0.5f),) {
                Button(
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.updateClaimScreen(1) }) {
                    Text("Monuments")
                }
                if (mode == 0) {
                    Divider()
                }
            }
        }

        if (mode == 0) {
            Column (modifier = Modifier.fillMaxSize().padding(16.dp)){
                TextField(value = potFieldValue,
                    onValueChange = {value :String -> onPotChange(value)},
                    label = {Text(text = "Nombre de poteaux")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onCancelButtonClicked) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = onNextButtonClicked) {
                        Text(text = "Confirm")
                    }
                }
            }

        } else {
            Text(text= "Ouais la jsp pas encore mais y'aura un truc je crois")
        }

    }
}