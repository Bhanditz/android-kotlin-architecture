package com.mooveit.kotlin.kotlintemplateproject.domain.repository

import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import io.reactivex.Observable
import retrofit2.Response

interface PetRepository {

    fun getPets(): Observable<List<Pet>>

    fun getPet(petId: Long): Observable<Pet>

    fun deletePet(petId: Long): Observable<Response<Void>>
}