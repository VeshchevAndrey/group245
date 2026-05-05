package com.example.application245

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val status: Boolean = false,
    val priority: Int = 0,
    val dueDate: String = ""
)
