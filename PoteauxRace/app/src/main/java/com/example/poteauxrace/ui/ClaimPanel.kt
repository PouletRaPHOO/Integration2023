package com.example.poteauxrace.ui

import android.content.Context
import android.location.Location
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.poteauxrace.common.Place
import java.lang.Double.max
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


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
    monuments : List<Place>,
    location : Location,
    idChosen : Int,
    onMonCLicked: (Int) -> Unit,
    onNextButtonClickedMon : () -> Unit
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
                if (mode == 0) {
                    Divider(thickness = 2.dp)
                }
            }
            Column(modifier = Modifier.weight(0.5f)) {
                Button(
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.updateClaimScreen(1) }) {
                    Text("Monuments")
                }
                if (mode == 1) {
                    Divider(thickness = 2.dp)
                }
            }
        }

        if (mode == 0) {
            Column (horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier
                .fillMaxSize()
                .padding(16.dp,)){
                TextField(value = potFieldValue,
                    onValueChange = {value :String -> onPotChange(value)},
                    label = {Text(text = "Nombre de poteaux")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
                Spacer(modifier = modifier.height(16.dp))
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
            var li = mutableListOf<Place>()
            for (mon in monuments) {
                li.add(mon.copy(distance = sqrt((mon.location[0]-location.latitude).pow(2)+  (mon.location[1]-location.longitude ).pow(2))))
            }
            li.sortBy { it.distance }
            var li2 = mutableListOf<Place>()
            for(k in 0..2) {
                li2.add(li[k])
            }


            Column (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)){
                Text("Selectionner Monument")
                Box(modifier = modifier) {
                    LazyColumn{
                        items(li2) {
                            MonCard(isSelected = (idChosen == it.id), id = it.id, name = it.name, onMonCLicked = onMonCLicked, pts = it.pts )
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onCancelButtonClicked) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = onNextButtonClickedMon) {
                        Text(text = "Confirm")
                    }
                }
            }
        }

    }
}

@Composable
fun MonCard(modifier: Modifier = Modifier, isSelected : Boolean, name: String, pts : Int,id : Int, onMonCLicked : (Int) -> Unit ) {
    var color = MaterialTheme.colorScheme.secondaryContainer
    var border = BorderStroke(width = 0.dp, Color.Black)
    if (isSelected) {
        border = BorderStroke(width = 1.dp, color = Color.Black)
        color = MaterialTheme.colorScheme.tertiaryContainer
    }
    Box (modifier = Modifier
        .background(color)
        .border(border = border)
        .padding(12.dp)
        .clickable(onClick = { onMonCLicked(id) }) ){
        

        Column() {
            Row(modifier.fillMaxWidth()) {
                Text(text = name)
                Text(text = "$pts Points", textAlign = TextAlign.End, modifier = modifier.weight(1f))
            }
        }
    }
}