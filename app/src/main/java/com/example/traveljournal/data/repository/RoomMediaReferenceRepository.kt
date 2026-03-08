package com.example.traveljournal.data.repository

import com.example.traveljournal.data.local.MediaReferenceDao
import com.example.traveljournal.data.local.MediaReferenceEntity
import com.example.traveljournal.domain.repository.MediaReferenceRepository

class RoomMediaReferenceRepository(
    private val mediaReferenceDao: MediaReferenceDao
) : MediaReferenceRepository {
    override suspend fun save(relativePath: String, createdAtEpochMillis: Long): Long {
        return mediaReferenceDao.insert(
            MediaReferenceEntity(
                relativePath = relativePath,
                createdAtEpochMillis = createdAtEpochMillis
            )
        )
    }

    override suspend fun getRelativePath(id: Long): String? {
        return mediaReferenceDao.getById(id)?.relativePath
    }
}
