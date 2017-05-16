package com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource

import com.mooveit.kotlin.kotlintemplateproject.data.cache.CacheManager
import com.mooveit.kotlin.kotlintemplateproject.data.cache.PetCache
import com.mooveit.kotlin.kotlintemplateproject.data.network.PetStoreService

class PetDataStoreFactory internal constructor(private val cache: PetCache,
                                               private val cacheManager: CacheManager,
                                               private val service: PetStoreService) {

    private fun getCloudPetDataStore(): CloudPetDataStore {
        return CloudPetDataStore(service, cache)
    }

    private fun getDataStore(predicate: Boolean): PetDataStore {
        val petDataStore: PetDataStore
        if (predicate) {
            petDataStore = DiskPetDataStore(cache)
        } else {
            petDataStore = getCloudPetDataStore()
        }

        return petDataStore
    }

    fun forPet(petId: Long): PetDataStore {
        return getDataStore(!cacheManager.isExpired() && cache.isCached(petId))
    }

    fun forPetList(): PetDataStore {
        return getDataStore(!cacheManager.isExpired() && cache.isPetListCached())
    }

    fun forDelete(): PetDataStore {
        return getCloudPetDataStore()
    }
}
