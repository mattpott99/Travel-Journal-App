package com.example.traveljournal.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MediaStorageSmokeTest {
    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()
    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        context.deleteDatabase(TEST_DB_NAME)
        database = Room.databaseBuilder(context, AppDatabase::class.java, TEST_DB_NAME).build()
    }

    @After
    fun cleanup() {
        database.close()
        context.deleteDatabase(TEST_DB_NAME)
    }

    @Test
    fun persistsReferenceAndFileAcrossReopen() = runBlocking {
        val fileStorage = LocalFileStorage(context)
        val tester = MediaStorageSmokeTester(fileStorage, database.mediaReferenceDao())

        val id = tester.run().getOrThrow()

        database.close()
        database = Room.databaseBuilder(context, AppDatabase::class.java, TEST_DB_NAME).build()

        val reloadedTester = MediaStorageSmokeTester(fileStorage, database.mediaReferenceDao())
        assertTrue(reloadedTester.verify(id))
    }

    private companion object {
        const val TEST_DB_NAME = "media-smoke-test.db"
    }
}
