package com.example.hospitalmanagementsystem.ui.common.tasks.data.model

data class ModelAllTasks(
    val `data`: List<TasksData>,
    val message: String,
    val status: Int
)

data class TasksData(
    val created_at: String,
    val id: Int,
    val status: String,
    val task_name: String
)