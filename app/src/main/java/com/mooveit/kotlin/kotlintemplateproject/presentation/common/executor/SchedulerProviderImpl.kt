package com.mooveit.kotlin.kotlintemplateproject.presentation.common.executor

import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.SchedulerProvider

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Singleton
class SchedulerProviderImpl @Inject
constructor() : SchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
