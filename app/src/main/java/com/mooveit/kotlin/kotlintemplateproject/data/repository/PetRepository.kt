package com.mooveit.kotlin.kotlintemplateproject.data.repository

import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import com.mooveit.kotlin.kotlintemplateproject.data.network.PetStoreService
import io.reactivex.Observable

class PetRepository(val petStoreService: PetStoreService) {

    fun petsAvailable(): Observable<List<Pet>> = petStoreService.petsAvailable

    fun createPet(pet: Pet): Observable<Pet> = petStoreService.createPet(pet)

    fun updatePet(pet: Pet): Observable<Pet> = petStoreService.updatePet(pet)

    fun deletePet(petId: Long): Observable<Void> = petStoreService.deletePet(petId)

    fun getPet(petId: Long): Observable<Pet> = petStoreService.getPet(petId)
}