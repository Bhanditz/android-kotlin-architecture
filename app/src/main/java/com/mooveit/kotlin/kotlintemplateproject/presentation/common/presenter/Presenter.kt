package com.mooveit.kotlin.kotlintemplateproject.presentation.common.presenter

import android.view.View
import com.mooveit.kotlin.kotlintemplateproject.domain.interactor.UseCase

open class Presenter(private val useCase: UseCase<*, *>) {

    open fun onResume(view: View) {}

    open fun onPause() {
        useCase.dispose()
    }

    open fun onDestroy() {
    }
}