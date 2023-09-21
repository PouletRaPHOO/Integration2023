package com.example.poteauxrace

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poteauxrace.ui.AppViewModel
import com.example.poteauxrace.ui.ClaimPanel
import com.example.poteauxrace.ui.MapScreen
import com.example.poteauxrace.ui.ObjectiveScreen
import com.example.poteauxrace.ui.TeamSelect

enum class AppScreen {
    TeamSelect,
    ObjectiveList,
    MainScreen,
    Claim
}

@Composable
fun App(modifier : Modifier = Modifier, viewModel : AppViewModel = viewModel()) {
    val navController = rememberNavController()

    val uiState by viewModel.uiState.collectAsState()

    var destination : String = AppScreen.TeamSelect.name
    if (uiState.isGameLaunched && uiState.isTeamChosen) {
        destination = AppScreen.MainScreen.name
    }

    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = destination
    ) {
        composable(route = AppScreen.TeamSelect.name) {
            TeamSelect(
                onButtonClicked = {teamId : Int ->
                    viewModel.updateTeam(teamId)
                },
                onDetectClicked = {
                    viewModel.updateRequest()
                    if (uiState.isGameLaunched) {
                        navController.navigate(AppScreen.MainScreen.name)
                    }
                },
                teams = uiState.teams,
                team = uiState.teamId
            )
        }
        composable(route = AppScreen.ObjectiveList.name) {
            ObjectiveScreen(modifier = Modifier ,
                onButtonClicked = {navController.popBackStack(AppScreen.MainScreen.name, inclusive = false)},
                objectives = uiState.objectives
            )
        }
        composable(route = AppScreen.MainScreen.name) {
            MapScreen(modifier = Modifier,
                onButtonClicked = {
                    navController.navigate(route = AppScreen.Claim.name)
                },
                onButtonObjClicked = {
                    navController.navigate(route = AppScreen.ObjectiveList.name)
                },
                onButtonRefreshClicked = {
                    viewModel.updateRequest()
                },
                poteaux = uiState.pot,
                monuments = uiState.places,
                teams = uiState.teams
            )
        }
        composable(route = AppScreen.Claim.name ) {
            ClaimPanel(
                onNextButtonClicked = {
                    val hasWorked = viewModel.onConfirmPot()
                    if (hasWorked) {
                        navController.popBackStack(AppScreen.MainScreen.name, inclusive = false)
                    }
                },
                onCancelButtonClicked = {
                    viewModel.onCancelPot()
                    navController.popBackStack(route = AppScreen.MainScreen.name, inclusive = false)
                },
                viewModel = viewModel,
                mode = uiState.claimMode,
                potFieldValue = uiState.potNumberField,
                onPotChange = {it : String -> viewModel.onPotChange(it) }
            )
        }
    }

}
