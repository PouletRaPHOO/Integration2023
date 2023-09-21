package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.poteauxrace.common.Objective

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectiveScreen(modifier : Modifier = Modifier, onButtonClicked: () -> Unit,objectives : List<Objective>) {
    Scaffold(floatingActionButton = {FloatingActionButton(onClick = onButtonClicked) {
        Icon(Icons.Rounded.Close, contentDescription = null)
    }} ) { it : PaddingValues ->
        LazyColumn {
            items(objectives) {obj ->
                ObjectiveCard(name = obj.name, pts = obj.pts, desc = obj.description, modifier = Modifier.padding((it)) )
            }
        }
    }



}

@Composable
fun ObjectiveCard(modifier: Modifier = Modifier, name : String, pts : Int, desc : String) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        Row (modifier = modifier.fillMaxWidth()) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = "${pts} Points", color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.End, modifier = modifier.weight(1f), fontWeight = FontWeight.Bold)
        }
        Text(text = desc)
    }
}