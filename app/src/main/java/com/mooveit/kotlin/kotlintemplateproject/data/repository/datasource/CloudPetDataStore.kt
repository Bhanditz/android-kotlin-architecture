package com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource

import com.mooveit.kotlin.kotlintemplateproject.data.cache.PetCache
import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import com.mooveit.kotlin.kotlintemplateproject.data.network.PetStoreService
import io.reactivex.Observable
import retrofit2.Response

internal class CloudPetDataStore(private val restApi: PetStoreService, private val cache: PetCache) : PetDataStore {

    override fun getPet(petId: Long): Observable<Pet> {
        return restApi.getPet(petId)
    }

    override fun getPetList(): Observable<List<Pet>> {
        return restApi.petsAvailable.doOnNext { petList: List<Pet>? -> petList?.let { cache.save(petList) } }
    }

    override fun createPet(pet: Pet): Observable<Pet> {
        return restApi.createPet(pet)
    }

    override fun updatePet(pet: Pet): Observable<Pet> {
        return restApi.updatePet(pet)
    }

    override fun deletePet(petId: Long): Observable<Response<Void>> {
        return restApi.deletePet(petId).doOnComplete { cache.delete(petId)}
    }
}
