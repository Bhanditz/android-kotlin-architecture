package com.mooveit.kotlin.kotlintemplateproject.domain.excecutor

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun main(): Scheduler
}
