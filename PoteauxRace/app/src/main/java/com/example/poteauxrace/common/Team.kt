package com.example.poteauxrace.common

import androidx.compose.ui.graphics.Color

data class Team(val id : Int = 0, val name : String, val color : Color)

data class Place(val id:Int, val name:String, val locLong : Int, val locLAt : Int, val pts : Int)

data class Objective(val name : String, val description: String, val pts : Int)