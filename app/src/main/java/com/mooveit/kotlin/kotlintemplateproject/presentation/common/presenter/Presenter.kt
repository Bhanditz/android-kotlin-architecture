package com.mooveit.kotlin.kotlintemplateproject.presentation.common.presenter

import android.view.View
import com.mooveit.kotlin.kotlintemplateproject.domain.interactor.UseCase

open class Presenter(vararg useCases: UseCase<*, *>) {

    private val useCasesList: List<UseCase<*, *>> = ArrayList()

    init {
        for (useCase in useCases) {
            useCasesList.plus(useCase)
        }
    }

    open fun onResume(view: View) {}

    open fun onPause() {
        for (useCase in useCasesList) {
            useCase.dispose()
        }
    }

    open fun onDestroy() {
    }
}