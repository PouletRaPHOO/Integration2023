package com.example.poteauxrace.ui

import androidx.compose.ui.graphics.Color
import com.example.poteauxrace.common.Objective
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.Team

data class AppStateUi(
    val isGameLaunched: Boolean = false,
    val isTeamChosen : Boolean = false,
    val teamId: Int? = null,
    val claimMode : Int = 0,
    val potNumberField : String = "",
    val isEntryPotWrong : Boolean = false,
    val teams : List<Team> = listOf<Team>(Team(0, "Red", Color.Red),
        Team(1, "Blue", Color.Blue),
        Team(2, "Green", Color.Green),
        Team(3, "Purple", Color.Magenta),
        Team(4, "Yellow", Color.Yellow),),
    val places : List<Place> = listOf<Place>(),
    val objectives : List<Objective> = listOf<Objective>(),
    )