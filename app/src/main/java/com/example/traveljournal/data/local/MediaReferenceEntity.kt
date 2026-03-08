package com.example.traveljournal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_references")
data class MediaReferenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val relativePath: String,
    val createdAtEpochMillis: Long
)
