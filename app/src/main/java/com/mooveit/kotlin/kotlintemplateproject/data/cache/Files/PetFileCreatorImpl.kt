package com.mooveit.kotlin.kotlintemplateproject.data.cache.Files

import android.content.Context
import java.io.File

class PetFileCreatorImpl internal constructor(context: Context) : PetFileCreator {

    private val cacheDir: File = context.cacheDir

    private val DEFAULT_FILE_NAME = "pet_"
    private val PET_LIST_FILE_NAME = "list_" + DEFAULT_FILE_NAME + "save"

    override fun getPetListFile(): File {
        return File(filePathNameBuilder()
                .append(PET_LIST_FILE_NAME)
                .toString())
    }

    override fun getPetFile(petId: Int): File {
        return File(filePathNameBuilder()
                .append(DEFAULT_FILE_NAME)
                .append(petId)
                .toString())
    }

    private fun filePathNameBuilder(): StringBuilder {
        return StringBuilder()
                .append(cacheDir.path)
                .append(File.separator)
    }
}
