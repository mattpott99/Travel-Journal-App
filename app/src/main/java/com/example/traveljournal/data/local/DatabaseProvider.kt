package com.example.traveljournal.data.local

import android.content.Context
import androidx.room.Room

class DatabaseProvider(context: Context) {
    private val applicationContext = context.applicationContext

    val databaseResult: Result<AppDatabase> by lazy {
        runCatching {
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    companion object {
        const val DB_NAME = "travel_journal.db"
    }
}
