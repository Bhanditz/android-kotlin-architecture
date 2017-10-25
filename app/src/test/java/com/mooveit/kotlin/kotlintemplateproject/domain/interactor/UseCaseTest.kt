package com.mooveit.kotlin.kotlintemplateproject.domain.interactor

import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.PostExecutionThread
import com.mooveit.kotlin.kotlintemplateproject.domain.excecutor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    private var useCase: UseCaseTestClass? = null

    private var testObserver: TestDisposableObserver<Any>? = null

    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread

    @Rule
    @JvmField
    var expectedException = ExpectedException.none()

    @Before
    fun setUp() {
        this.useCase = UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread)
        this.testObserver = TestDisposableObserver<Any>()
        given(mockPostExecutionThread.scheduler).willReturn(TestScheduler())
    }

    @Test
    fun testBuildUseCaseObservableReturnCorrectResult() {
        useCase!!.execute(testObserver!!, Params.EMPTY)

        assertThat(testObserver!!.valuesCount).isZero
    }

    @Test
    fun testSubscriptionWhenExecutingUseCase() {
        useCase!!.run {
            execute(testObserver!!, Params.EMPTY)
            dispose()
        }

        assertThat(testObserver!!.isDisposed).isTrue
    }

    @Suppress("UNREACHABLE_CODE")
    @Test
    fun testShouldFailWhenExecuteWithNullObserver() {
        expectedException.expect(NullPointerException::class.java)
        useCase!!.execute(null!!, Params.EMPTY)
    }

    private class UseCaseTestClass internal constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread)
        : UseCase<Any, Params>(threadExecutor, postExecutionThread) {

        override fun buildUseCaseObservable(params: Params?): Observable<Any> {
            return Observable.empty<Any>()
        }

        override fun execute(observer: DisposableObserver<Any>, params: Params?) {
            super.execute(observer, params)
        }
    }

    private class TestDisposableObserver<T> : DisposableObserver<T>() {

        internal var valuesCount = 0

        override fun onNext(value: T) {
            valuesCount++
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    }

    private object Params {
        val EMPTY = Params
    }
}