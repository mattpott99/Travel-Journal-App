package com.example.traveljournal.data.local

import com.example.traveljournal.domain.repository.MediaReferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaStorageSmokeTester(
    private val fileStorage: LocalFileStorage,
    private val mediaReferenceRepository: MediaReferenceRepository
) {
    suspend fun run(): Result<Long> = withContext(Dispatchers.IO) {
        val path = fileStorage.writeDummyMediaFile(
            fileName = "smoke-test.bin",
            contents = "travel-journal".encodeToByteArray()
        ).getOrElse { return@withContext Result.failure(it) }

        val id = mediaReferenceRepository.save(
            relativePath = path,
            createdAtEpochMillis = System.currentTimeMillis()
        )

        Result.success(id)
    }

    suspend fun verify(id: Long): Boolean = withContext(Dispatchers.IO) {
        val path = mediaReferenceRepository.getRelativePath(id) ?: return@withContext false
        fileStorage.exists(path)
    }
}
