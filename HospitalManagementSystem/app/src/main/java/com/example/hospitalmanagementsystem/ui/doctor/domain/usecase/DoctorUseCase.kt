package com.example.hospitalmanagementsystem.ui.doctor.domain.usecase

import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCasesDetails
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelSuccess

import com.example.hospitalmanagementsystem.ui.doctor.data.repo.IDoctorRepo
import retrofit2.http.Path
import javax.inject.Inject

class DoctorUseCase @Inject constructor(private val iRepo: IDoctorRepo) {
    suspend fun getDoctorCalls(): ModelDoctorCalls{
        return iRepo.getDoctorCalls()

    }

    suspend fun getAcceptORRejectCall( id: Int,status:String):ModelSuccess{
        return iRepo.getAcceptORRejectCall(id,status)
    }

    suspend fun getDoctorCases(): ModelDoctorCases{
        return iRepo.getDoctorCases()
    }
    suspend fun getCaseById( id: Int): ModelDoctorCasesDetails{
        return iRepo.getCaseById(id)

    }
    suspend fun addNurse(caseId: Int, doctorId: Int):ModelSuccess{
        return iRepo.addNurse(caseId,doctorId)
    }

    suspend fun requestAnalysis (callId : Int, userId : Int, note : String, types : List<String> ) : ModelSuccess{
        return iRepo.requestAnalysis(callId,userId,note,types)

    }
}