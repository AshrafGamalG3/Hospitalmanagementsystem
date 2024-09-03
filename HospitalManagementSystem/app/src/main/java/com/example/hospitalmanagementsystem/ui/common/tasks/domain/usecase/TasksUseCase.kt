package com.example.hospitalmanagementsystem.ui.common.tasks.domain.usecase

import com.example.hospitalmanagementsystem.ui.common.tasks.data.repo.ITasksRepo
import javax.inject.Inject

class TasksUseCase @Inject constructor(private val repo: ITasksRepo){

    suspend fun getAllTasks(date: String) = repo.showAllTasks(date)

    suspend fun createTasks(userId: Int, taskName: String, description: String, todoList: List<String>) = repo.createTasks(userId, taskName, description, todoList)

    suspend fun execution(id: Int, note: String) = repo.execution(id, note)

    suspend fun showTaskDetails(id: Int) = repo.showTaskDetails(id)

}