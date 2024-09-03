package com.example.hospitalmanagementsystem.ui.nurse.data

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import retrofit2.http.Field

interface INurseRepo {

    suspend fun uploadMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String,
    ): ModelCreation

    suspend fun sendNotification(
        userId: Int,
        title: String,
        body: String,
    ):ModelCreation
}