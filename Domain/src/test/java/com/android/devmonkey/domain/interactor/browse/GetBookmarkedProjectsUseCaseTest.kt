package com.android.devmonkey.domain.interactor.browse

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.model.Project
import com.android.devmonkey.domain.repository.ProjectRepository
import com.android.devmonkey.domain.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsUseCaseTest {

    private lateinit var getBookmarkedProjectsUseCase: GetBookmarkedProjectsUseCase
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjectsUseCase = GetBookmarkedProjectsUseCase(projectRepository, postExecutionThread)
    }

    @After
    fun tearDown() {
        // Do Nothing
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkedProjectsUseCase.buildUseCase().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projectList = ProjectDataFactory.makeProjectList(2)
        stubGetBookmarkedProjects(Observable.just(projectList))
        val testObserver = getBookmarkedProjectsUseCase.buildUseCase().test()
        testObserver.assertValue(projectList)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<Project>>) {
        whenever(projectRepository.getBookmarkedProjects())
                .thenReturn(observable)
    }
}