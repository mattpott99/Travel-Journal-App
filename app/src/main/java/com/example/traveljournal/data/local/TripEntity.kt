package com.example.traveljournal.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "trips",
    indices = [Index(value = ["ownerId", "name"], unique = true)]
)
data class TripEntity(
    @PrimaryKey val id: String,
    val ownerId: String,
    val name: String
)
