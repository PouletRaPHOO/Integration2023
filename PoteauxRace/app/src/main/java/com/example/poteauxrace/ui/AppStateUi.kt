package com.example.poteauxrace.ui

import android.location.Location
import androidx.compose.ui.graphics.Color
import com.example.poteauxrace.common.Objective
import com.example.poteauxrace.common.Place
import com.example.poteauxrace.common.Pot
import com.example.poteauxrace.common.Team
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState

data class AppStateUi(
    val isGameLaunched: Boolean = false,
    val isTeamChosen : Boolean = false,
    val teamId: Int? = null,
    val claimMode : Int = 0,
    val potNumberField : String = "",
    val isEntryPotWrong : Boolean = false,
    val teams : List<Team> = listOf<Team>(Team(0, "Red", Color.Red, 0f),
        Team(1, "Blue", Color.Blue, 240f),
        Team(2, "Green", Color.Green, 120f),
        Team(3, "Purple", Color.Magenta, 270f),
        Team(4, "Yellow", Color.Yellow, 60f),),
    val places : MutableList<Place> = mutableListOf<Place>(
        Place(0, name = "Hotel De Ville", 10, false, whoClaimedit = null ,listOf<Double>(45.7574,4.8326)),
        Place(1, name = "Basilique de Fourvière", 10, false, whoClaimedit = null ,listOf<Double>(45.7629,4.8221)),
        Place(2, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(3, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(4, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(5, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(6, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(7, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(8, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(9, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(10, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(11, name = "A", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),



        ),
    val objectives : List<Objective> = listOf<Objective>(
        Objective(name = "Fallait être là", description = "Ramener un carreau du sol du lycée du Parc", pts = 15),
        Objective(name = "La Saint Vache", description = "Ramener la vache rouge, du bar éponyme", pts = 1500),
        Objective(name = "Pierrot dans mon coeur", description = "Une photo dédicacée de Pierre Bel", pts = 1500),
        Objective(name = "Ramener un objet", description = "Un caillou (mais joli)", pts = 1),
        Objective(name = "Ramener un objet", description = "Une grue grandeur nature (c'est pour mon TIPE)", pts = 1500),
        Objective(name = "Ramener un objet", description = "Ramener un élève consentant du lycée du Parc", pts = 15),
        Objective(name = "Pereant barbari administratioque", description = "Ramener un membre de l'administration du lycée du Parc", pts = 15),
        Objective(name = "Un grand classique", description = "Ramener un plot de chantier", pts = 15),
        Objective(name = "Un grand classique", description = "Ramener un plot de chantier", pts = 15),
        ),
    val pot : List<Pot> = listOf(),
    val startTime : Int = 0,
    val hasClaimWorked : Boolean = true,
    val location: Location = Location(null)
    )