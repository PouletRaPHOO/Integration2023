package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TeamSelect(modifier : Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        TeamButton(teamId = 0, teamName = "Red")
        TeamButton(teamId = 1, teamName = "Blue")
        TeamButton(teamId = 2, teamName = "Blue")
        TeamButton(teamId = 3, teamName = "Blue")
        TeamButton(teamId = 4, teamName = "Blue")


    }
}

@Composable
fun TeamButton(modifier: Modifier = Modifier, teamId:Int, teamName :String) {
    Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(24.dp)) {
        Text(text=teamName, modifier = Modifier.padding(15.dp))
    }
}
