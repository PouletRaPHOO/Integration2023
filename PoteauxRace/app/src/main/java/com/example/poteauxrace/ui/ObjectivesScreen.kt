package com.example.poteauxrace.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.poteauxrace.common.Objective

@Composable
fun ObjectiveScreen(modifier : Modifier = Modifier, onButtonClicked: () -> Unit,objectives : List<Objective>) {
    IconButton(onClick = onButtonClicked) {
        //Icon()
    }
    LazyColumn {
        items(objectives) {obj ->
            ObjectiveCard(name = obj.name, pts = obj.pts, desc = obj.description )
        }
    }
}

@Composable
fun ObjectiveCard(modifier: Modifier = Modifier, name : String, pts : Int, desc : String) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Row (modifier = modifier.fillMaxWidth()) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(40.dp))
            Text(text = "${pts} Points", color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.End, modifier = modifier.weight(1f), fontWeight = FontWeight.Bold)
        }
        Text(text = desc)
    }
}