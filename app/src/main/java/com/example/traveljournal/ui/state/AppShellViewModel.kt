package com.example.traveljournal.ui.state

import androidx.lifecycle.ViewModel
import com.example.traveljournal.TravelJournalApplication

class AppShellViewModel(app: TravelJournalApplication) : ViewModel() {
    val state: AppInitState = app.databaseProvider.databaseResult
        .fold(
            onSuccess = { AppInitState.Ready },
            onFailure = { AppInitState.DbInitFailed(it.message ?: "Unknown DB initialization error") }
        )
}
