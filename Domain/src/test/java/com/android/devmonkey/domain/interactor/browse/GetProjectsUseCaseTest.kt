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

class GetProjectsUseCaseTest {

    private lateinit var getProjectsUseCase : GetProjectsUseCase
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjectsUseCase = GetProjectsUseCase(projectRepository, postExecutionThread)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        // Do Nothing ...
    }

    @Test
    @Throws(Exception::class)
    fun getProjectsShouldComplete() {
        stubGetProject(Observable.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getProjectsUseCase.buildUseCase().test()
        testObserver.assertComplete()
    }

    @Test
    @Throws(Exception::class)
    fun getProjectsShouldReturnsProjectsObservableList() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProject(Observable.just(projects))

        val testObserver = getProjectsUseCase.buildUseCase().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetProject(observable: Observable<List<Project>>) {
        whenever(projectRepository.getProjects())
                .thenReturn(observable)
    }
}