package com.mooveit.kotlin.kotlintemplateproject.data.cache

import com.google.gson.reflect.TypeToken
import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.PetFileCreator
import com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer.FileSerializer
import com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer.Serializer
import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import io.reactivex.Observable

class PetCacheImpl internal constructor(private val serializer: Serializer,
                                        private val fileSerializer: FileSerializer,
                                        private val cacheManager: CacheManager,
                                        private val petFileCreator: PetFileCreator) : PetCache {

    override fun isPetListCached(): Boolean {
        return cacheManager.isCached(petFileCreator.getPetListFile())
    }

    override fun isCached(petId: Long): Boolean {
        return cacheManager.isCached(petFileCreator.getPetFile(petId.toInt()))
    }

    override fun get(petId: Int): Observable<Pet> {
        return fileSerializer.getData(petFileCreator.getPetFile(petId),
                { fileContent -> serializer.deserialize(fileContent, Pet::class.java) })
    }

    override fun getPetList(): Observable<List<Pet>> {
        return fileSerializer.getData(petFileCreator.getPetListFile(),
                { fileContent -> serializer.deserialize(fileContent, object : TypeToken<ArrayList<Pet>>() {}) })
    }

    override fun save(petList: List<Pet>) {
        val petEntityFile = petFileCreator.getPetListFile()
        if (!cacheManager.isCached(petEntityFile)) {
            cacheManager.setFileCacheData(petEntityFile, { serializer.serialize(petList) })
        }
    }

    override fun delete(petId: Long) {
        cacheManager.getFileCacheData(petFileCreator.getPetListFile(), { fileContent ->
            val petList = serializer.deserialize(fileContent, object : TypeToken<ArrayList<Pet>>() {})
            petList.find { pet -> pet.externalId == petId }?.let { petList.remove(it) }
            save(petList)
        })
    }
}