package com.mooveit.kotlin.kotlintemplateproject.data.cache.Files

import android.content.Context
import android.content.SharedPreferences
import java.io.*

/**
 * Helper class to do operations on regular files/directories.
 */
class FileManager internal constructor() {

    /**
     * Writes a file to Disk.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform this operation using another thread.

     * @param file The file to write to Disk.
     */
    internal fun writeToFile(file: File, fileContent: String) {
        if (!file.exists()) {
            try {
                val writer = FileWriter(file)
                writer.write(fileContent)
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Reads a content from a file.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.

     * @param file The file to read from.
     * *
     * @return A string with the content of the file.
     */
    internal fun readFileContent(file: File): String {
        val fileContentBuilder = StringBuilder()
        if (file.exists()) {
            try {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                var line: String? = null
                while ({ line = bufferedReader.readLine(); line }() != null) {
                    fileContentBuilder.append(line).append("\n")
                }
                bufferedReader.close()
                fileReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return fileContentBuilder.toString()
    }

    /**
     * Returns a boolean indicating whether this file can be found on the underlying file system.

     * @param file The file to check existence.
     * *
     * @return true if this file exists, false otherwise.
     */
    internal fun exists(file: File): Boolean {
        return file.exists()
    }

    /**
     * Warning: Deletes the content of a directory.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.

     * @param directory The directory which its content will be deleted.
     */
    internal fun clearDirectory(directory: File): Boolean {
        var result = false
        if (directory.exists()) {
            for (file in directory.listFiles()) {
                result = file.delete()
            }
        }
        return result
    }

    /**
     * Write a value to a user preferences file.

     * @param context [android.content.Context] to retrieve android user preferences.
     * *
     * @param preferenceFileName A file name reprensenting where data will be written to.
     * *
     * @param key A string for the key that will be used to retrieve the value in the future.
     * *
     * @param value A long representing the value to be inserted.
     */
    internal fun writeToPreferences(context: Context, preferenceFileName: String, key: String, value: Long) {
        getPreferences(context, preferenceFileName).edit().apply {
            this.putLong(key, value)
            this.apply()
        }
    }

    /**
     * Get a value from a user preferences file.

     * @param context [android.content.Context] to retrieve android user preferences.
     * *
     * @param preferenceFileName A file name representing where data will be get from.
     * *
     * @param key A key that will be used to retrieve the value from the preference file.
     * *
     * @return A long representing the value retrieved from the preferences file.
     */
    internal fun getFromPreferences(context: Context, preferenceFileName: String, key: String): Long {
        return getPreferences(context, preferenceFileName).getLong(key, 0)
    }

    private fun getPreferences(context: Context, preferenceFileName: String): SharedPreferences {
        return context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
    }
}