package com.mooveit.kotlin.kotlintemplateproject.data.cache.Files

import java.io.File

interface PetFileCreator {

    fun getPetFile(petId: Int): File

    fun getPetListFile(): File
}