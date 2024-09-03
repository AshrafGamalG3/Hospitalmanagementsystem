package com.example.hospitalmanagementsystem.ui.common.reports.data.repo

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelAllReports
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelShowReport

interface IReportRepo {

    suspend fun getAllReports(date: String): ModelAllReports
    suspend fun endReport(id: Int): ModelCreation
    suspend fun showReport(id: Int): ModelShowReport
    suspend fun answerReport(id: Int, answer: String): ModelCreation
    suspend fun createReport(reportName: String, description: String): ModelCreation

}