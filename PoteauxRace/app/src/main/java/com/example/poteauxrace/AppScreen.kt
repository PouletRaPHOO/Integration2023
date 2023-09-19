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
import com.example.poteauxrace.ui.ClaimPanel
import com.example.poteauxrace.ui.MapScreen
import com.example.poteauxrace.ui.TeamSelect

enum class AppScreen {
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
    if (uiState.isGameLaunched && uiState.isTeamChosen) {
        destination = AppScreen.MainScreen.name
    }

    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = AppScreen.MainScreen.name
    ) {
        composable(route = AppScreen.TeamSelect.name) {
            TeamSelect(
                onButtonClicked = {teamId : Int ->
                    viewModel.updateTeam(teamId)
                    if (uiState.isGameLaunched) {
                        navController.navigate(route = AppScreen.MainScreen.name)
                    } else {
                        navController.navigate(route = AppScreen.WaitingForStart.name)
                    }
                },
                onDetectClicked = {
                    /* runBlocking {
                        async {
                            var isGameLaunched : Boolean = viewModel.gamelaunchRequest()
                        }
                        if (isGameLaunched.await()) {
                            viewModel.updateGameStatus(true)
                            navController.navigate(AppScreen.MainScreen.name)
                        }
                    }*/

                }
            )
        }
        /*composable(route = AppScreen.WaitingForStart.name) {
            WaitingScreen(modifier = Modifier ,
                onGameLaunched = {
                    viewModel.updateGameStatus(true)
                    navController.navigate(route = AppScreen.MainScreen.name)
                }
            )
        }*/
        composable(route = AppScreen.MainScreen.name) {
            MapScreen(modifier = Modifier,
                onButtonClicked = {
                    navController.navigate(route = AppScreen.Claim.name)
                }
            )
        }
        composable(route = AppScreen.Claim.name ) {
            ClaimPanel(
                onNextButtonClicked = {
                        navController.popBackStack(AppScreen.MainScreen.name, inclusive = false)
                },
                onCancelButtonClicked = {
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
