package com.mooveit.kotlin.kotlintemplateproject.presentation.view.home

import android.support.annotation.NonNull
import android.view.View
import com.mooveit.kotlin.kotlintemplateproject.data.entity.Pet
import com.mooveit.kotlin.kotlintemplateproject.domain.interactor.GetPetList
import com.mooveit.kotlin.kotlintemplateproject.presentation.common.presenter.Presenter
import com.mooveit.kotlin.kotlintemplateproject.presentation.internal.di.scope.PerActivity
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@PerActivity
class HomePresenter @Inject constructor(private val mUseCase: GetPetList) : Presenter(mUseCase) {

    private var mHomeView: HomeView? = null

    override fun onResume(view: View) {
        super.onResume(view)
        setView(view as HomeView)
    }

    override fun onDestroy() {
        super.onPause()
        this.mHomeView = null
    }

    fun setView(@NonNull homeView: HomeView) {
        this.mHomeView = homeView
    }

    fun fetchPets() {
        mHomeView!!.showProgress()
        mUseCase.execute(PetListObserver(), null)
    }

    private inner class PetListObserver : DisposableObserver<List<Pet>>() {

        override fun onNext(pets: List<Pet>) {
            mHomeView!!.hideProgress()
            mHomeView!!.showPets(pets)
        }

        override fun onComplete() {

        }

        override fun onError(error: Throwable) {
            mHomeView!!.hideProgress()
            error.message?.let { mHomeView!!.showError(it) }
            mHomeView!!.showPets()
        }
    }
}