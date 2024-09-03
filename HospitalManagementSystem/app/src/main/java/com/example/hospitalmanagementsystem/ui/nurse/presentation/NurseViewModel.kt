package com.example.hospitalmanagementsystem.ui.nurse.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.nurse.domain.usecase.NurseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NurseViewModel @Inject constructor(private val nurseUseCase: NurseUseCase) : ViewModel() {
    private var _uploadMeasurement = MutableLiveData<ModelCreation>()
    val uploadMeasurement get() = _uploadMeasurement
    private var _sendNotification = MutableLiveData<ModelCreation>()
    val sendNotification get() = _sendNotification


    fun uploadMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String,
    ) {
        viewModelScope.launch {
            try {
                _uploadMeasurement.value = nurseUseCase.uploadMeasurement(
                    caseId,
                    bloodPressure,
                    sugarAnalysis,
                    tempreture,
                    fluidBalance,
                    respiratoryRate,
                    heartRate,
                    not,
                    status
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendNotification(doctorId: Int, title: String, body: String) {
        viewModelScope.launch {
            try {
                _sendNotification.value = nurseUseCase.sendNotification(doctorId, title, body)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}