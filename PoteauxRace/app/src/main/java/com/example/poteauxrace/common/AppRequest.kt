package com.example.poteauxrace.common

import kotlinx.serialization.Serializable

@Serializable
data class UpdateResponse (
    val uIsReady : Boolean,
    val uPoteaux : List<Pot>,
    val uMonuments : List<PlaceR>,
    val uScores : List<List<Int>>,
    val  uStartTime : Long,
)



@Serializable
data class PotClaim (
    val tag : String = "u",
    val cLoc : List<Double>,
    val cDate : Long,
    val cpNb : Int,
    val cTeam : Int,
)
@Serializable
data class MonClaim (
    val tag : String = "u",
    val cDate : Int,
    val cMonId : Int,
    val cTeam : Int,
)