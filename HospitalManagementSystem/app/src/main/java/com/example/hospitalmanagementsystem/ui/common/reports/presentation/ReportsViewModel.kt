package com.example.hospitalmanagementsystem.ui.common.reports.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelAllReports
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelShowReport
import com.example.hospitalmanagementsystem.ui.common.reports.domain.usecase.ReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(private val reportsUseCase: ReportUseCase) :
    ViewModel() {

    private var _reports = MutableLiveData<ModelAllReports>()
    val reports get() = _reports

    private var _showReport = MutableLiveData<ModelShowReport>()
    val showReport get() = _showReport

    private var _endReport = MutableLiveData<ModelCreation>()
    val endReport get() = _endReport

    private var _createReport = MutableLiveData<ModelCreation>()
    val createReport get() = _createReport


    private var _answerReport = MutableLiveData<ModelCreation>()
    val answerReport get() = _answerReport


    fun getAllReports(date: String) {
        viewModelScope.launch {
            try {
                val data = reportsUseCase.getAllReports(date)
                _reports.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showReport(id: Int) {
        viewModelScope.launch {
            try {
                val data = reportsUseCase.showReport(id)
                _showReport.value = data
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun endReport(id: Int) {
        viewModelScope.launch {
            try {
                val data = reportsUseCase.endReport(id)
                _endReport.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createReport(reportName: String, description: String) {
        viewModelScope.launch {
            try {
                val data = reportsUseCase.createReport(reportName, description)
                _createReport.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun answerReport(id: Int, answer: String) {
        viewModelScope.launch {
            try {
                val data = reportsUseCase.answerReport(id, answer)
                _answerReport.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}