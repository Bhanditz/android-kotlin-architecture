package com.mooveit.kotlin.kotlintemplateproject.presentation.excecutor

import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.PostExecutionThread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}
