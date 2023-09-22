package com.example.poteauxrace.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.poteauxrace.common.Team


@Composable
fun TeamSelect(modifier : Modifier = Modifier, onButtonClicked : (Int) -> Unit, onDetectClicked: () -> Unit, teams : List<Team>, team : Int) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (team != null) {
            Text(text = "Your Team is ${teams.get(team).name}")
        }

        Text(text = "Choose your team", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            items(teams) { team : Team->
                TeamButton(teamId = team.id, teamName = team.name, onButtonClicked = onButtonClicked)
            }
        }
        if (team != null) {
            Button(onClick = onDetectClicked) {
                Text(text = "Detect Game Launch")
            }
        }
    }
}

@Composable
fun TeamButton(modifier: Modifier = Modifier, teamId:Int, teamName :String, onButtonClicked : (Int) -> Unit) {
    Button(onClick = {onButtonClicked(teamId)}, modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)
        .background(color = MaterialTheme.colorScheme.primaryContainer)) {
        Text(text=teamName, modifier = Modifier.padding(15.dp))
    }
}
