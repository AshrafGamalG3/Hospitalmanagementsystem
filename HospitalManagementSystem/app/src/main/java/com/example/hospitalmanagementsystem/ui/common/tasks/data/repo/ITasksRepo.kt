package com.example.hospitalmanagementsystem.ui.common.tasks.data.repo

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelAllTasks
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelTaskDetails

interface ITasksRepo {

    suspend fun showAllTasks(date: String): ModelAllTasks
    suspend fun createTasks(
        userId: Int, taskName: String, description: String, todoList: List<String>
    ): ModelCreation

    suspend fun execution(id: Int, note: String): ModelCreation

    suspend fun showTaskDetails(id: Int): ModelTaskDetails

}