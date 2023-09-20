package com.example.poteauxrace.common

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import org.json.JSONArray

data class Team(val id : Int = 0, val name : String, val color : Color,)
@Serializable
data class PlaceR(val teamId: Int, val id:Int, val name:String, val pLocation : List<Int>)

data class Place(val id:Int, val name:String, val pts : Int, val hasBeenClaimed : Boolean = false, val whoClaimedit : Int?)

data class Objective(val name : String, val description: String, val pts : Int)
@Serializable
data class Pot(val pDate : Int, val pLocation : List<Int>, val pNb : Int, val pTeam : Int)
sealed interface RequestState {
    data class Success(val response : JSONArray) : RequestState
    object Error: RequestState
    object Loading : RequestState
}