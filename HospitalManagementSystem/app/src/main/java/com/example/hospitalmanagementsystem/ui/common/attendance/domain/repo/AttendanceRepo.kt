package com.example.hospitalmanagementsystem.ui.common.attendance.domain.repo

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.common.attendance.data.repo.IAttendanceRepo
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import javax.inject.Inject

class AttendanceRepo @Inject constructor(private val apiService: ApiCalls) : IAttendanceRepo {
    override suspend fun attendance(status: String): ModelCreation {
        return apiService.attendance(status)
    }
}