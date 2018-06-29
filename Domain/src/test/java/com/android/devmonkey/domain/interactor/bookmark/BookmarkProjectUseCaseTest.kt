package com.android.devmonkey.domain.interactor.bookmark

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.repository.ProjectRepository
import com.android.devmonkey.domain.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectUseCaseTest {

    private lateinit var bookmarkProjectUseCase: BookmarkProjectUseCase
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkProjectUseCase = BookmarkProjectUseCase(projectRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProjectUseCase.buildUseCaseCompletable(
                BookmarkProjectUseCase.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectWithoutParamsExpectException() {
        val testObserver = bookmarkProjectUseCase.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectRepository.bookmarkProject(any()))
                .thenReturn(completable)
    }

}