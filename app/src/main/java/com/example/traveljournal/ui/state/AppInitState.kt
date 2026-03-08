package com.example.traveljournal.ui.state

sealed interface AppInitState {
    data object Ready : AppInitState
    data class DbInitFailed(val message: String) : AppInitState
}
