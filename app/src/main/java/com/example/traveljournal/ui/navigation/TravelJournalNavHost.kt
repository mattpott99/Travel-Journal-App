package com.example.traveljournal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.traveljournal.TravelJournalApplication
import com.example.traveljournal.ui.screens.HomeScreen
import com.example.traveljournal.ui.screens.TripLibraryPlaceholderScreen
import com.example.traveljournal.ui.screens.DatabaseErrorScreen
import com.example.traveljournal.ui.state.AppInitState
import com.example.traveljournal.ui.state.AppShellViewModel

@Composable
fun TravelJournalNavHost(app: TravelJournalApplication) {
    val state = remember(app) { AppShellViewModel(app).state }
    if (state is AppInitState.DbInitFailed) {
        DatabaseErrorScreen(state.message)
        return
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.HOME) {
        composable(Destinations.HOME) {
            HomeScreen(onOpenLibraryPlaceholder = {
                navController.navigate(Destinations.LIBRARY_PLACEHOLDER)
            })
        }
        composable(Destinations.LIBRARY_PLACEHOLDER) {
            TripLibraryPlaceholderScreen()
        }
    }
}
