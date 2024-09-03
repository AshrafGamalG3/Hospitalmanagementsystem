package com.example.hospitalmanagementsystem.ui.doctor.data.repo

import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCasesDetails
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelSuccess

import retrofit2.http.Path
import retrofit2.http.Query

interface IDoctorRepo {
    suspend fun getDoctorCalls(): ModelDoctorCalls
    suspend fun getAcceptORRejectCall(id: Int,status:String):ModelSuccess
    suspend fun getDoctorCases(): ModelDoctorCases
    suspend fun getCaseById( id: Int): ModelDoctorCasesDetails
    suspend fun addNurse(caseId: Int, doctorId: Int):ModelSuccess
    suspend fun requestAnalysis (callId : Int, userId : Int, note : String, types : List<String> ) : ModelSuccess


}