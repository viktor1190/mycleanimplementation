package com.android.devmonkey.domain.interactor.bookmark

import com.android.devmonkey.domain.executor.PostExecutionThread
import com.android.devmonkey.domain.interactor.CompletableUseCase
import com.android.devmonkey.domain.repository.ProjectRepository
import io.reactivex.Completable
import javax.inject.Inject

class UnbookmarkProjectUsecase @Inject constructor(
        private val projectRepository: ProjectRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<UnbookmarkProjectUsecase.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectRepository.unbookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String) = Params(projectId)
        }
    }
}