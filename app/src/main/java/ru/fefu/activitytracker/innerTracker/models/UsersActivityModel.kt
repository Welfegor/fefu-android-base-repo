package ru.fefu.activitytracker.innerTracker.models

import java.time.LocalDateTime

data class UsersActivityModel(
    val distance: String,
    val taskType: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val username: String,
)