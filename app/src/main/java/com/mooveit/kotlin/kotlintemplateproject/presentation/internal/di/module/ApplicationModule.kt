package com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.module

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }
}