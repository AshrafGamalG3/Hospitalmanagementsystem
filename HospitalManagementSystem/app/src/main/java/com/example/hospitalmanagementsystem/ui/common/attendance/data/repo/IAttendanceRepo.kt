package com.example.hospitalmanagementsystem.ui.common.attendance.data.repo

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation

interface IAttendanceRepo {


    suspend fun attendance(status: String): ModelCreation

}