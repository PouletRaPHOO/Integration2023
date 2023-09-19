package com.example.poteauxrace.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TeamSelect(modifier : Modifier = Modifier, onButtonClicked : (Int) -> Unit, onDetectClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Choose your team", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Column() {
            TeamButton(teamId = 0, teamName = "Red", onButtonClicked = onButtonClicked)
            TeamButton(teamId = 1, teamName = "Blue", onButtonClicked = onButtonClicked)
            TeamButton(teamId = 2, teamName = "Green", onButtonClicked = onButtonClicked)
            TeamButton(teamId = 3, teamName = "Yellow", onButtonClicked = onButtonClicked)
            TeamButton(teamId = 4, teamName = "Violet", onButtonClicked = onButtonClicked)
        }
        Button(onClick = onDetectClicked) {
            Text(text = "Detecter changement de Game")
        }
    }
}

@Composable
fun TeamButton(modifier: Modifier = Modifier, teamId:Int, teamName :String, onButtonClicked : (Int) -> Unit) {
    Button(onClick = {onButtonClicked(teamId)}, modifier = Modifier.fillMaxWidth().padding(24.dp).background(color = MaterialTheme.colorScheme.primaryContainer )) {
        Text(text=teamName, modifier = Modifier.padding(15.dp))
    }
}
