package com.mooveit.kotlin.kotlintemplateproject.data.cache

import android.content.Context
import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.FileManager
import java.io.File

class CacheManager internal constructor(context: Context,
                                        private val fileManager: FileManager,
                                        private val threadExecutor: ThreadExecutorManager) {

    private val SETTINGS_FILE_NAME = "com.mooveit.kotlin.SETTINGS"
    private val SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update"
    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private val context: Context = context.applicationContext
    private val cacheDir: File = context.cacheDir

    fun isCached(file: File): Boolean {
        return fileManager.exists(file)
    }

    fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = lastCacheUpdateTimeMillis
        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME
        return expired.apply { if (this) threadExecutor.executeAsynchronously(eraseCacheData()) }
    }

    fun getFileCacheData(file: File, unit: (content: String) -> Unit) {
        threadExecutor.executeAsynchronously(Runnable {
            val fileContent = fileManager.readFileContent(file)
            unit(fileContent)
        })
    }

    fun setFileCacheData(fileToWrite: File, fileContent: () -> String) {
        threadExecutor.executeAsynchronously(Runnable {
            fileManager.writeToFile(fileToWrite, fileContent())
            setLastCacheUpdateTimeMillis()
        })
    }

    fun eraseCacheData(): Runnable {
        return Runnable {
            fileManager.clearDirectory(cacheDir)
        }
    }

    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        fileManager.writeToPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis)
    }

    private val lastCacheUpdateTimeMillis: Long
        get() = fileManager.getFromPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE)
}