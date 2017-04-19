package com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.component

import android.app.Application

import com.mooveit.kotlin.kotlintemplateproject.presentation.PetApp
import com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.module.RepositoryModule
import com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.module.ApplicationModule
import com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.module.NetworkModule

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RepositoryModule::class, NetworkModule::class))
interface ApplicationComponent {

    fun inject(app: PetApp)

    fun application(): Application
}