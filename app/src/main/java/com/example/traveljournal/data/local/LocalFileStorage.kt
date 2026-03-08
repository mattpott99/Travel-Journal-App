package com.example.traveljournal.data.local

import android.content.Context
import java.io.File
import java.io.IOException

class LocalFileStorage(context: Context) {
    private val rootDir = File(context.filesDir, "media").apply { mkdirs() }

    fun writeDummyMediaFile(fileName: String, contents: ByteArray): Result<String> {
        return runCatching {
            val target = File(rootDir, fileName)
            target.outputStream().use { it.write(contents) }
            target.relativeTo(context.filesDirSafe()).path
        }
    }

    fun exists(relativePath: String): Boolean = File(context.filesDirSafe(), relativePath).exists()

    private val context = context.applicationContext

    private fun Context.filesDirSafe(): File {
        val dir = filesDir ?: throw IOException("App files dir unavailable.")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }
}
