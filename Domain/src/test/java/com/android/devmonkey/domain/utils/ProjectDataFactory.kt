package com.android.devmonkey.domain.utils

import com.android.devmonkey.domain.model.Project
import java.util.*

class ProjectDataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun randomeBoolean(): Boolean = Math.random() < 0.5

    fun makeProject(): Project = Project(
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomeBoolean())

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}