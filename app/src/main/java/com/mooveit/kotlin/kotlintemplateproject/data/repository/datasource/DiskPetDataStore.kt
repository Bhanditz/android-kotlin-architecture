package com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource

import com.mooveit.kotlin.kotlintemplateproject.data.cache.PetCache
import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet

import io.reactivex.Observable
import retrofit2.Response

internal class DiskPetDataStore(private val petCache: PetCache) : PetDataStore {

    override fun getPet(petId: Long): Observable<Pet> {
        return Observable.just(Pet())
    }

    override fun getPetList(): Observable<List<Pet>> {
        return petCache.getPetList()
    }

    override fun createPet(pet: Pet): Observable<Pet> {
        return Observable.just(Pet())
    }

    override fun updatePet(pet: Pet): Observable<Pet> {
        return Observable.just(Pet())
    }

    override fun deletePet(petId: Long): Observable<Response<Void>> {
        return Observable.just(null)
    }
}