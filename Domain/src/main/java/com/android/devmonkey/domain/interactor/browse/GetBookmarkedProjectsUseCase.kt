package com.android.devmonkey.domain.interactor.browse

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.interactor.ObservableUseCase
import com.android.devmonkey.domain.model.Project
import com.android.devmonkey.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjectsUseCase @Inject constructor(
        private val projectRepository: ProjectRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing>(postExecutionThread){

    override fun buildUseCase(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getBookmarkedProjects()
    }
}