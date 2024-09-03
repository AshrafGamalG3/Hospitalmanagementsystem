package com.example.hospitalmanagementsystem.ui.common.tasks.domain.repo

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelAllTasks
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelTaskDetails
import com.example.hospitalmanagementsystem.ui.common.tasks.data.repo.ITasksRepo
import javax.inject.Inject

class TasksRepo @Inject constructor(private val apiService: ApiCalls) : ITasksRepo {
    override suspend fun showAllTasks(date: String): ModelAllTasks {
        return apiService.showAllTasks(date)
    }

    override suspend fun createTasks(
        userId: Int,
        taskName: String,
        description: String,
        todoList: List<String>,
    ): ModelCreation {
       return apiService.createTasks(userId,taskName,description,todoList)
    }

    override suspend fun execution(id: Int, note: String): ModelCreation {
      return apiService.execution(id,note)
    }

    override suspend fun showTaskDetails(id: Int): ModelTaskDetails {
        return apiService.showTask(id)
    }

}