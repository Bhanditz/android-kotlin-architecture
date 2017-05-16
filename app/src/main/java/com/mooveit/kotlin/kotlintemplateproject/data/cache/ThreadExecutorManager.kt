package com.mooveit.kotlin.kotlintemplateproject.data.cache

import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.ThreadExecutor

class ThreadExecutorManager internal constructor(private val threadExecutor: ThreadExecutor) {

    fun executeAsynchronously(runnable: Runnable) {
        threadExecutor.execute(runnable)
    }
}
