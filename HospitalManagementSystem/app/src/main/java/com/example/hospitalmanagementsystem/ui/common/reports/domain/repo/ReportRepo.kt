package com.example.hospitalmanagementsystem.ui.common.reports.domain.repo

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelAllReports
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelShowReport
import com.example.hospitalmanagementsystem.ui.common.reports.data.repo.IReportRepo
import javax.inject.Inject

class ReportRepo @Inject constructor(private val apiCalls: ApiCalls):IReportRepo {
    override suspend fun getAllReports(date: String): ModelAllReports {
        return apiCalls.getAllReports(date)
    }

    override suspend fun endReport(id: Int): ModelCreation {
        return apiCalls.endReport(id)
    }

    override suspend fun showReport(id: Int): ModelShowReport {
       return apiCalls.showReport(id)
    }

    override suspend fun answerReport(id: Int, answer: String): ModelCreation {
   return apiCalls.answerReport(id,answer)
    }

    override suspend fun createReport(reportName: String, description: String): ModelCreation {
       return apiCalls.createReport(reportName,description)
    }

}