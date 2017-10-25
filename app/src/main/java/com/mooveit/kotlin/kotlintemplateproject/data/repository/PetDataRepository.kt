package com.mooveit.kotlin.kotlintemplateproject.data.repository

import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource.PetDataStoreFactory
import com.mooveit.kotlin.kotlintemplateproject.domain.repository.PetRepository
import io.reactivex.Observable
import retrofit2.Response

class PetDataRepository(private val petDataStoreFactory: PetDataStoreFactory) : PetRepository {

    override fun getPet(petId: Long): Observable<Pet> {
        return petDataStoreFactory.forPet(petId).getPet(petId)
    }

    override fun getPets(): Observable<List<Pet>> {
        return petDataStoreFactory.forPetList().getPetList()
    }

    override fun deletePet(petId: Long): Observable<Response<Void>> {
        return petDataStoreFactory.forDelete().deletePet(petId)
    }
}