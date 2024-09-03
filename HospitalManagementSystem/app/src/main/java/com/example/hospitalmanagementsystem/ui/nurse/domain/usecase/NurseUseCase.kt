package com.example.hospitalmanagementsystem.ui.nurse.domain.usecase

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.nurse.domain.repo.NurseRepo
import javax.inject.Inject

class NurseUseCase @Inject constructor(private val nurseRepo: NurseRepo) {

    suspend fun  uploadMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String,
    ) : ModelCreation {
        return nurseRepo.uploadMeasurement(caseId, bloodPressure, sugarAnalysis, tempreture, fluidBalance, respiratoryRate, heartRate, not, status)

    }
    suspend fun sendNotification(userId: Int, title: String, body: String):ModelCreation{
        return nurseRepo.sendNotification(userId, title, body)

    }
}