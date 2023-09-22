package com.example.poteauxrace.common

import kotlinx.serialization.Serializable

@Serializable
data class UpdateResponse (
    val uIsReady : Boolean,
    val uPoteaux : List<Pot>,
    val uMonuments : List<PlaceR>,
    val uScores : List<List<Int>>,
    val  uStartTime : Int,
)



@Serializable
data class PotClaim (
    val tag : String = "u",
    val cLoc : List<Double>,
    val cDate : Int,
    val cpNb : Int,
    val cTeam : Int,
)