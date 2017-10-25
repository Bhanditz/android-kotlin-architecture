package com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource

import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import io.reactivex.Observable
import retrofit2.Response

interface PetDataStore {

    fun getPet(petId: Long): Observable<Pet>

    fun getPetList(): Observable<List<Pet>>

    fun createPet(pet: Pet): Observable<Pet>

    fun updatePet(pet: Pet): Observable<Pet>

    fun deletePet(petId: Long): Observable<Response<Void>>
}