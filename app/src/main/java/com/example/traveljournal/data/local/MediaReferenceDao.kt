package com.example.traveljournal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MediaReferenceDao {
    @Insert
    suspend fun insert(entity: MediaReferenceEntity): Long

    @Query("SELECT * FROM media_references WHERE id = :id")
    suspend fun getById(id: Long): MediaReferenceEntity?
}
