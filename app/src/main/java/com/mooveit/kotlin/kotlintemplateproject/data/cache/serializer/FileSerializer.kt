package com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer

import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.FileManager
import io.reactivex.Observable
import java.io.File

class FileSerializer internal constructor(private val fileManager: FileManager) {

    fun <T> getData(file: File, entity: (fileContent: String) -> T): Observable<T> {
        return Observable.create<T> { observable ->
            val fileContent = fileManager.readFileContent(file)
            observable.onNext(entity(fileContent))
            observable.onComplete()
        }
    }
}