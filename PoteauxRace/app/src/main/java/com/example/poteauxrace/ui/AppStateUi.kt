package com.example.poteauxrace.ui

data class AppStateUi(
    val isGameLaunched: Boolean = false,
    val isTeamChosen : Boolean = false,
    val teamId: Int? = null,
    val claimMode : Int = 0,
    )