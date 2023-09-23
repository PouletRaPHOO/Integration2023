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
        Place(0, name = "Hotel De Ville", 10, false, whoClaimedit = null ,listOf<Double>(45.764043,4.8356)),
        Place(1, name = "Basilique de Fourvière", 10, false, whoClaimedit = null ,listOf<Double>(45.7623998,4.82283)),
        Place(2, name = "Le Gros Caillou", 10, false, whoClaimedit = null ,listOf<Double>(45.774,4.83500)),
        Place(3, name = "Le Véritable Théatre des Guignols", 10, false, whoClaimedit = null ,listOf<Double>(45.77647,4.8536)),
        Place(4, name = "Le Grand Manchot", 10, false, whoClaimedit = null ,listOf<Double>(45.783123,4.849901)),
        Place(5, name = "Atelier du Quai", 10, false, whoClaimedit = null ,listOf<Double>(45.77647,4.841453)),
        Place(6, name = "Cathédrale Saint Jean", 10, false, whoClaimedit = null ,listOf<Double>(45.760780,4.826687)),
        Place(7, name = "ENS Lyon", 10, false, whoClaimedit = null ,listOf<Double>(45.733129, 4.833510)),
        Place(8, name = "Confluences", 10, false, whoClaimedit = null ,listOf<Double>(3.0  ,3.0)),
        Place(9, name = "La Longue Traboule", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(10, name = "Zoo de Lyon", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(11, name = "Jardin des Curiosités", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(12, name = "Shruberry", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(13, name = "Laverie Lyon 6", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(14, name = "Mineralia", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(15, name = "Hammamet", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(16, name = "Magasin de Chaussettes", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(17, name = "Kiloshop", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(18, name = "Fontaine Bartholdi", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),
        Place(19, name = "Musée du Cinéma", 10, false, whoClaimedit = null ,listOf<Double>(3.0,3.0)),




        ),
    val objectives : List<Objective> = listOf<Objective>(
        Objective(name = "Fallait être là", description = "Ramener un carreau du sol du lycée du Parc", pts = 8),
        Objective(name = "La Saint Vache", description = "Ramener la vache rouge, du bar éponyme", pts = 1500),
        Objective(name = "Pierrot dans mon coeur", description = "Une photo dédicacée de Pierre Bel", pts = 1500),
        Objective(name = "Ramener un objet", description = "Un caillou (mais joli)", pts = 1),
        Objective(name = "Ramener un objet", description = "Une grue grandeur nature (c'est pour mon TIPE)", pts = 1500),
        Objective(name = "Ramener un objet", description = "Ramener un élève consentant du lycée du Parc (Hors classe)", pts = 7),
        Objective(name = "Ramener un objet", description = "Ramener un élève non consentant du lycée du Parc (Hors classe)", pts = 75),
        Objective(name = "Ramener un objet", description = "2 points par objet de la couleur de l'équipe dans la rue (Règle soumise à changements, peut varier si objet incongru)", pts = 2),
        Objective(name = "Pereant barbari administratioque", description = "Ramener un membre de l'administration du lycée du Parc", pts = 150),
        Objective(name = "Un grand classique", description = "Ramener un plot de chantier", pts = 15),
        Objective(name = "Vive les chauves", description = "4pts par Selfie Avec un chauve dans la rue, Compte double si c'est une vidéo", pts = 4),
        Objective(name = "L'eau ça mouille...", description = "Une photo d'un membre du groupe (n'importe lequel) dans la fontaine des Terreaux", pts = 25),
        Objective(name = "Pax Romana", description = "Une photo du groupe faisant un doigt au lycée des Lazaristes (à moins de 20m du lycée)", pts = 25),
        Objective(name = "Amen", description = "Un verre d'eau de la fontaine du Lycée", pts = 10),
        Objective(name = "Il m'a forcé je le jure", description = "Faire un calin à Volkan", pts = 0),
        Objective(name = "Joyeux anniversaire Mano", description = "Souhaiter un bon anniversaire à Mano (il doit être la physiquement ou en vidéo) (Nécessite une preuve vidéo)", pts = 20),




        ),
    val pot : List<Pot> = listOf(),
    val startTime : Long = 0,
    val hasClaimWorked : Boolean = true,
    val location: Location = Location(null),
    val idMonumentChosen : Int = 0,
    val hasMonumentBeenChosed : Boolean = false
    )