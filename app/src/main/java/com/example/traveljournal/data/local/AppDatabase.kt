package com.example.traveljournal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TripEntity::class, MediaReferenceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mediaReferenceDao(): MediaReferenceDao
}
