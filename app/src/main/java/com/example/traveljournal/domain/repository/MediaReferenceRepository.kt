package com.example.traveljournal.domain.repository

interface MediaReferenceRepository {
    suspend fun save(relativePath: String, createdAtEpochMillis: Long): Long
    suspend fun getRelativePath(id: Long): String?
}
