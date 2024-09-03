package com.example.hospitalmanagementsystem.ui.common.tasks.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelAllTasks
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelTaskDetails
import com.example.hospitalmanagementsystem.ui.common.tasks.domain.usecase.TasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val useCase: TasksUseCase) : ViewModel() {

    private var _tasks = MutableLiveData<ModelAllTasks>()
    val tasks get() = _tasks

    private var _execution = MutableLiveData<ModelCreation>()
    val execution get() = _execution

    private var _taskDetails = MutableLiveData<ModelTaskDetails>()
    val taskDetails get() = _taskDetails


    fun getAllTasks(date: String) {
        viewModelScope.launch {
            try {
                val response = useCase.getAllTasks(date)
                _tasks.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun execution(id: Int, note: String) {
        viewModelScope.launch {
            try {
                val response = useCase.execution(id, note)
                _execution.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showTaskDetails(id: Int) {
        viewModelScope.launch {
            try {
                val response = useCase.showTaskDetails(id)
                _taskDetails.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}