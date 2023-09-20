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