package com.android.devmonkey.domain.interactor.browse

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.repository.ProjectRepository
import org.junit.After
import org.junit.Before
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
}