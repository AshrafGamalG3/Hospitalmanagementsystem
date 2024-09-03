package com.example.hospitalmanagementsystem.ui.doctor.domain.repo

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCasesDetails
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelSuccess

import com.example.hospitalmanagementsystem.ui.doctor.data.repo.IDoctorRepo
import javax.inject.Inject

class DoctorRepo @Inject constructor(private val apiCalls: ApiCalls) :IDoctorRepo {
    override suspend fun getDoctorCalls(): ModelDoctorCalls {
        return apiCalls.getDoctorCalls()
    }

    override suspend fun getAcceptORRejectCall(id: Int, status: String):ModelSuccess {
        return apiCalls.getAcceptORRejectCall(id,status)

    }

    override suspend fun getDoctorCases(): ModelDoctorCases {
        return apiCalls.getDoctorCases()
    }

    override suspend fun getCaseById(id: Int): ModelDoctorCasesDetails {
        return apiCalls.getCaseById(id)
    }

    override suspend fun addNurse(caseId: Int, doctorId: Int): ModelSuccess {
        return apiCalls.addNurse(caseId,doctorId)
    }

    override suspend fun requestAnalysis(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): ModelSuccess {
        return apiCalls.requestAnalysis(callId,userId,note,types)
    }


}