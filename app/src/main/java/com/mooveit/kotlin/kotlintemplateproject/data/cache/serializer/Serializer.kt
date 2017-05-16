package com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet

class Serializer internal constructor(private val gson: Gson) {

    fun serialize(any: Any): String {
        return gson.toJson(any)
    }

    fun serialize(any: Any, clazz: Class<*>): String {
        return gson.toJson(any, clazz)
    }

    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }

    fun <T> deserialize(string: String, typeToken: TypeToken<T>): T {
        return gson.fromJson(string, typeToken.type)
    }
}