package com.android.devmonkey.domain.interactor.bookmark

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.repository.ProjectRepository
import com.android.devmonkey.domain.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import net.bytebuddy.pool.TypePool
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkProjectUsecaseTest {

    private lateinit var unbookmarkProjectUsecase: UnbookmarkProjectUsecase
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProjectUsecase = UnbookmarkProjectUsecase(projectRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectComplete() {
        stubUnbookmarkProject(Completable.complete())
        val testObserver = unbookmarkProjectUsecase.buildUseCaseCompletable(
                UnbookmarkProjectUsecase.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectWithoutParamsExpectException() {
        unbookmarkProjectUsecase.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectRepository.unbookmarkProject(any()))
                .thenReturn(completable)
    }
}