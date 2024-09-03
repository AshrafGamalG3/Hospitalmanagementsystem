package com.example.hospitalmanagementsystem.ui.common.reports.domain.usecase

import com.example.hospitalmanagementsystem.ui.common.reports.data.repo.IReportRepo
import javax.inject.Inject

class ReportUseCase @Inject constructor(private val iReportRepo: IReportRepo) {


    suspend fun getAllReports(date: String) = iReportRepo.getAllReports(date)
    suspend fun endReport(id: Int) = iReportRepo.endReport(id)
    suspend fun showReport(id: Int) = iReportRepo.showReport(id)
    suspend fun answerReport(id: Int, answer: String) = iReportRepo.answerReport(id, answer)
    suspend fun createReport(reportName: String, description: String) =
        iReportRepo.createReport(reportName, description)
}