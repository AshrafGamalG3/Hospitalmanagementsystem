package com.example.hospitalmanagementsystem.ui.common.attendance.domain.usecase

import com.example.hospitalmanagementsystem.ui.common.attendance.data.repo.IAttendanceRepo
import com.example.hospitalmanagementsystem.ui.common.attendance.domain.repo.AttendanceRepo
import javax.inject.Inject

class AttendanceUseCase @Inject constructor(private val attendanceRepo: IAttendanceRepo) {

    suspend fun attendance(status: String) {
        attendanceRepo.attendance(status)
    }
}