package com.android.devmonkey.domain.model

data class Project(val id: String, val name: String, val fullname: String,
                   val startCount: String, val dateCreated: String,
                   val ownerName: String, val ownerAvatar: String,
                   val isBookmared: Boolean)