package com.example.traveljournal

import android.app.Application
import com.example.traveljournal.data.local.DatabaseProvider

class TravelJournalApplication : Application() {
    lateinit var databaseProvider: DatabaseProvider
        private set

    override fun onCreate() {
        super.onCreate()
        databaseProvider = DatabaseProvider(this)
    }
}
