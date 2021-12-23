package ru.fefu.activitytracker.innerTracker.models

import java.time.LocalDateTime

data class ActivityModel(
    val distance: String,
    val taskType: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)