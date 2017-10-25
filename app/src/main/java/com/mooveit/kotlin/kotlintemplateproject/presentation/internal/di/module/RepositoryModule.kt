package com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.module

import android.content.Context
import com.google.gson.Gson
import com.mooveit.kotlin.kotlintemplateproject.data.cache.CacheManager
import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.FileManager
import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.PetFileCreator
import com.mooveit.kotlin.kotlintemplateproject.data.cache.Files.PetFileCreatorImpl
import com.mooveit.kotlin.kotlintemplateproject.data.cache.PetCache
import com.mooveit.kotlin.kotlintemplateproject.data.cache.PetCacheImpl
import com.mooveit.kotlin.kotlintemplateproject.data.cache.ThreadExecutorManager
import com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer.FileSerializer
import com.mooveit.kotlin.kotlintemplateproject.data.cache.serializer.Serializer
import com.mooveit.kotlin.kotlintemplateproject.data.network.PetStoreService
import com.mooveit.kotlin.kotlintemplateproject.data.repository.PetDataRepository
import com.mooveit.kotlin.kotlintemplateproject.data.repository.datasource.PetDataStoreFactory
import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.ThreadExecutor
import com.mooveit.kotlin.kotlintemplateproject.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSerializeFile(fileManager: FileManager): FileSerializer {
        return FileSerializer(fileManager)
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutorManager(threadExecutor: ThreadExecutor): ThreadExecutorManager {
        return ThreadExecutorManager(threadExecutor)
    }

    @Provides
    @Singleton
    internal fun providePetFiles(context: Context): PetFileCreator {
        return PetFileCreatorImpl(context)
    }

    @Provides
    @Singleton
    internal fun provideCacheManager(context: Context, fileManager: FileManager,
                                     threadExecutor: ThreadExecutorManager): CacheManager {
        return CacheManager(context, fileManager, threadExecutor)
    }

    @Provides
    @Singleton
    internal fun provideFileManager(): FileManager {
        return FileManager()
    }

    @Provides
    @Singleton
    internal fun provideSerializer(gson: Gson): Serializer {
        return Serializer(gson)
    }

    @Provides
    @Singleton
    internal fun providePetCache(serializer: Serializer, fileSerializer: FileSerializer,
                                 cacheManager: CacheManager, petFileCreator: PetFileCreator): PetCache {
        return PetCacheImpl(serializer, fileSerializer, cacheManager, petFileCreator)
    }

    @Provides
    @Singleton
    internal fun providePetDataStoreFactory(petCache: PetCache, cacheManager: CacheManager, service: PetStoreService): PetDataStoreFactory {
        return PetDataStoreFactory(petCache, cacheManager, service)
    }

    @Provides
    @Singleton
    internal fun providePetRepository(factory: PetDataStoreFactory): PetRepository {
        return PetDataRepository(factory)
    }
}