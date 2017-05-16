package com.mooveit.kotlin.kotlintemplateproject.data.cache

import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import io.reactivex.Observable

interface PetCache {

    fun get(petId: Int): Observable<Pet>

    fun getPetList(): Observable<List<Pet>>

    fun save(petList: List<Pet>)

    fun delete(petId: Long)

    fun isPetListCached(): Boolean

    fun isCached(petId: Long): Boolean
}