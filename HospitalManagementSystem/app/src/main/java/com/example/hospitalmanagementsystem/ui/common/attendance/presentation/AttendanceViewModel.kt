package com.example.hospitalmanagementsystem.ui.common.attendance.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.common.attendance.domain.repo.AttendanceRepo
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(private val attendanceRepo: AttendanceRepo) : ViewModel()  {

    private var _attendanceStatus = MutableLiveData<ModelCreation>()
    val attendanceStatus get() = _attendanceStatus



    fun attendance(status: String) {
        viewModelScope.launch {
            _attendanceStatus.value = attendanceRepo.attendance(status)
        }
    }
}