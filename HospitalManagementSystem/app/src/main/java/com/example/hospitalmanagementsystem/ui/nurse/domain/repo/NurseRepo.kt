package com.example.hospitalmanagementsystem.ui.nurse.domain.repo

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.nurse.data.INurseRepo
import javax.inject.Inject

class NurseRepo @Inject constructor(private val apiCalls: ApiCalls): INurseRepo {
    override suspend fun uploadMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String,
    ): ModelCreation {
        return apiCalls.uploadMeasurement(caseId,bloodPressure,sugarAnalysis,tempreture,fluidBalance,respiratoryRate,heartRate,not,status)
    }

    override suspend fun sendNotification(userId: Int, title: String, body: String): ModelCreation {
        return apiCalls.sendNotification(userId,title,body)
    }
}