package com.example.poteauxrace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poteauxrace.ui.AppViewModel
import com.example.poteauxrace.ui.TeamSelect
import com.example.poteauxrace.ui.WaitingScreen

enum class AppScreen() {
    TeamSelect,
    WaitingForStart,
    MainScreen,
    Claim
}

@Composable
fun App(modifier : Modifier = Modifier, viewModel : AppViewModel = viewModel()) {
    val navController = rememberNavController()

    val uiState by viewModel.uiState.collectAsState()

    var destination : String = AppScreen.TeamSelect.name
    if (uiState.isGameLaunched) {
        destination = AppScreen.MainScreen.name
    }

    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = destination
    ) {
        composable(route = AppScreen.TeamSelect.name) {
            TeamSelect(
                onButtonClicked = {it -> viewModel.updateTeam(it)}
            )
        }
        composable(route = AppScreen.WaitingForStart.name) {
            WaitingScreen {

            }
        }
    }

}
